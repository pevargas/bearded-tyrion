package miki

import com.github.rjeschke.txtmark.*

class ArticleController {

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

    def view( )
    {
        String title = params.file
        def repo = Repository.list( )[0].location
        def filename = repo + "/" + title
        def corpus = ""
        new File( filename ).eachLine
        { 
            line -> 
            corpus += line + "\r\n"
        }

        def result = Processor.process( corpus )

        [title: title, content: result]
    }

    def update( )
    {
        String title = params.file
        def repo = Repository.list( )[0].location
        def filename = repo + "/" + title
        def corpus = ""
        new File( filename ).eachLine
        {
            line ->
            corpus += line + "\r\n"
        }

        [title: title, content: corpus]
    }

    def save( )
    {
        String title = params.file
        def repo = Repository.list( )[0].location
        def filename = repo + "/" + title
        def fh = new File( filename )
        fh << params.content

        flash.message = title + " has been updated!"
        redirect( action: "view", params: [file: title] )
    }

    def create( )
    {
        String filename = new Date().format("yyyy_MM_dd")
        filename += ".md"
        [title: filename]
    }
}
