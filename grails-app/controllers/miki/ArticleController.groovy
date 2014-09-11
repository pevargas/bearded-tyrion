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
        def corpus = ""
        new File( repo + "/" + title ).eachLine
        { 
            line -> 
            corpus += line + "\r\n"
        }

        def result = Processor.process( corpus )

        [title: title, content: result]
    }

    def edit( )
    {
        String title = params.file
        def repo = Repository.list( )[0].location
        def corpus = ""
        new File( repo + "/" + title ).eachLine
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
        new File( repo + "/" + title ).withWriter
        { 
            out ->
            out.writeLine( params.content )
        }
        flash.message = title + " has been updated!"
        redirect( action: "view", params: [file: title] )
    }
}
