package miki

import java.util.regex.Matcher
import java.util.regex.Pattern
import com.github.rjeschke.txtmark.*

class ArticleController
{
    def articleService

    // View the folder location
    def location( )
    {
        // Grab the directory
        render articleService.directory( )
    }

    // List all the files in the directory
    def index( )
    {
        // Grab the directory
        def repo = articleService.directory( )
        
        // List all the files in the directory
        def result = [ ]
        new File( repo ).eachFile( )
        { 
            file->
            def entry = [:]
            entry.filename = file.getName( )
            entry.human = articleService.humanTitle( entry.filename )
            result += entry
        }
        [articles: result]
    }

    // Make a new file
    def create( )
    {
        // Give the new file today's date
        String filename = new Date().format("yyyy_MM_dd_HH_mm_ss")
        filename += ".md"

        // Make it human readable
        def timestamp = new Date().format("yyyy/MM/dd HH:mm:ss")
        
        // Put in some default text
        def content = "Today is a new day...";

        // REturn information
        [title: filename, content: content, created: timestamp, tags: "these, are, example, tags"]
    }

    // View specific file
    def view( )
    {
        // Grab the file's full path
        def filename = articleService.fullPath( params.file )

        // Grab a human-friendly filename
        def human = articleService.humanTitle( params.file )
        
        // Separate the meta information form the content
        def data = articleService.parseFile( filename )

        // Convert Markdown to HTML
        def result = Processor.process( data.content )

        // Return information
        [title: params.file, human: human, content: result, created: data.created, updated: data.updated, tags: data.tags]
    }

    // Update the file
    def update( )
    {
        // Grab the file's full path
        def filename = articleService.fullPath( params.file )
        
        // Grab a human-friendly filename
        def human = articleService.humanTitle( params.file )

        // Separate the meta information from the content
        def data = articleService.parseFile( filename )
        
        // Return information
        [title: params.file, human: human, content: data.content, created: data.created, updated: data.updated, tags: data.tags]
    }

    // Save the edited file
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
