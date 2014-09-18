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
        def content = "Today is a new day...";

        [title: filename, content: content, created: timestamp, tags: "these, are, example, tags"]
    }

    def view( )
    {
        def filename = articleService.fullPath( params.file )
        def data = articleService.parseFile( filename )
        def result = Processor.process( data.content )

        [title: params.file, content: result, created: data.created, updated: data.updated, tags: data.tags]
    }

    def update( )
    {
        def filename = articleService.fullPath( params.file )
        def data = articleService.parseFile( filename )
        [title: params.file, content: data.content, created: data.created, updated: data.updated, tags: data.tags]
    }

    def save( )
    {
        // Remove the old file just in case we rename the file
        def oldfile = articleService.fullPath( params.old )
        new File( oldfile ).delete( )

        // Write the new content to the file
        def filename = articleService.fullPath( params.file )
        articleService.writeFile( filename, params )

        // Redirect to view the result
        flash.message = params.file + " has been updated!"
        redirect( action: "view", params: [file: params.file] )
    }

    def delete( )
    {
        def filename = articleService.fullPath( params.file )
        new File( filename ).delete()

        flash.message = params.file + " has been removed."
        redirect( action: "index", params: [file: params.file] )
    }
}
