package miki

import com.github.rjeschke.txtmark.*

class ArticleController {

    def index( )
    {
        def repo = Repository.list( )
        render repo[0].location
    }

    def list( )
    {
        def repo = Repository.list( )
        
        def result = [ ]
        new File( repo[0].location ).eachFile( )
        { 
            file->
            result += file.getName( )
        }
        [articles: result]
    }

    def view( )
    {
        String title = params.file
        def repo = Repository.list( )
        def content = ""
        new File( repo[0].location + "/" + title ).eachLine
        { 
            line -> 
            content += line + "\r\n"
        }

        def result = Processor.process( content )

        [title: title, body: result]
    }
}
