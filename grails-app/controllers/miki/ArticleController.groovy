package miki

import java.util.regex.Matcher
import java.util.regex.Pattern
import com.github.rjeschke.txtmark.*

class ArticleController {

    def createDate = "miki.dateCreated="
    def updateDate = "miki.lastUpdated="
    def tagList    = "miki.tags="

    def location( )
    {
        def repo = Repository.list( )[0].location
        render repo
    }

    def index( )
    {
        def repo = Repository.list( )[0].location
        
        def result = [ ]
        new File( repo ).eachFile( )
        { 
            file->
            result += file.getName( )
        }
        [articles: result]
    }

    def create( )
    {
        String filename = new Date().format("yyyy_MM_dd_HH_mm_ss")
        filename += ".md"

        def timestamp = new Date().format("yyyy/MM/dd HH:mm:ss")
        
        [title: filename, created: timestamp, tags: "these, are, example, tags"]
    }

    def view( )
    {
        String title = params.file
        def repo = Repository.list( )[0].location
        def filename = repo + "/" + title
        def fh = new File( filename )

        def corpus = ""
        def created, updated, tags
        fh.eachLine
        { 
            line -> 
            if ( line =~ createDate )
            {
                created = line.substring( createDate.length() )
            }
            else if ( line =~ updateDate )
            {
                updated = line.substring( createDate.length() )
            }
            else if ( line =~ tagList )
            {
                tags = line.substring( tagList.length() ).tokenize( "," )
            }
            else
            {
                corpus += line + "\r\n"
            }
        }

        def result = Processor.process( corpus )

        [title: title, content: result, created: created, updated: updated, tags: tags]
    }

    def update( )
    {
        String title = params.file
        def repo = Repository.list( )[0].location
        def filename = repo + "/" + title
        def corpus = ""
        def created, updated, tags
        new File( filename ).eachLine
        {
            line -> 
            if ( line =~ createDate )
            {
                created = line.substring( createDate.length() )
            }
            else if ( line =~ updateDate )
            {
                updated = line.substring( createDate.length() )
            }
            else if ( line =~ tagList )
            {
                tags = line.substring( tagList.length() ).tokenize( "," )
            }
            else
            {
                corpus += line + "\r\n"
            }
        }

        [title: title, content: corpus, created: created, updated: updated, tags: tags]
    }

    def save( )
    {
        String title = params.file
        if ( ! ( title =~ /\.md/ ) )
        {
            title += ".md"
        }
        title = title.replaceAll( "\\s", "_" )

        def repo = Repository.list( )[0].location
        def filename = repo + "/" + title

        new File( filename ).withWriter( )
        {
            out ->
            out.writeLine( createDate + params.created )
            out.writeLine( updateDate + new Date().format("yyyy/MM/dd HH:mm:ss") )
            out.writeLine( tagList + params.tags )
            out.writeLine( params.content )
        }

        flash.message = title + " has been updated!"
        redirect( action: "view", params: [file: title] )
    }

    def delete( )
    {
        String title = params.file
        def repo = Repository.list( )[0].location
        def filename = repo + "/" + title
        new File( filename ).delete()

        flash.message = title + " has been removed."
        redirect( action: "index", params: [file: title] )
    }
}
