package miki

import java.util.regex.Matcher
import java.util.regex.Pattern
import com.github.rjeschke.txtmark.*

class ArticleController
{
    def articleService
    def createDate = "miki.dateCreated="
    def updateDate = "miki.lastUpdated="
    def tagList    = "miki.tags="

    // View the folder location
    def location( )
    {
        def repo = Repository.list( )[0].location
        render repo
    }

    // List all the files in the directory
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

    // Make a new file
    def create( )
    {
        String filename = new Date().format("yyyy_MM_dd_HH_mm_ss")
        filename += ".md"

        def timestamp = new Date().format("yyyy/MM/dd HH:mm:ss")
        def content = "Today is a new day...";

        [title: filename, content: content, created: timestamp, tags: "these, are, example, tags"]
    }

    // View specific file
    def view( )
    {
        // Grab the file's full path
        def filename = articleService.fullPath( params.file )
        def corpus = ""
        def created, updated, tags

        // Separate the meta information form the content
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

        // Convert the content from Markdown to HTML
        def result = Processor.process( corpus )

        // Return
        [title: params.file, content: result, created: created, updated: updated, tags: tags]
    }

    // Update the file
    def update( )
    {
        // Grab the file's full path
        def filename = articleService.fullPath( params.file )
        def corpus = ""
        def created, updated, tags

        // Separate the meta information from the content
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

        // Return
        [title: params.file, content: corpus, created: created, updated: updated, tags: tags]
    }

    // Save the edited file
    def save( )
    {
        // Remove the old file just in case we rename the file
        def oldfile = articleService.fullPath( params.old )
        new File( oldfile ).delete( )

        // Write the new content to the file
        def filename = articleService.fullPath( params.file )
        new File( filename ).withWriter( )
        {
            out ->
            out.writeLine( createDate + params.created )
            out.writeLine( updateDate + new Date().format("yyyy/MM/dd HH:mm:ss") )
            out.writeLine( tagList + params.tags )
            out.writeLine( params.content )
        }

        // Redirect to view the result
        flash.message = params.file + " has been updated!"
        redirect( action: "view", params: [file: params.file] )
    }

    // Remove the file
    def delete( )
    {
        // Grab the file's full path
        def filename = articleService.fullPath( params.file )
        new File( filename ).delete()

        // Send user back to the homepage
        flash.message = params.file + " has been removed."
        redirect( action: "index", params: [file: params.file] )
    }
}
