package miki

<<<<<<< HEAD
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
        new File( repo[0].location ).eachFile( ) { file->
            result += file.getName( )
        }
        [articles: result]
    }

    def view( )
    {
        String title = params.file
        def repo = Repository.list( )
        def content
        new File( repo[0].location + "/" + title ).eachLine { line -> content += line }

        [title: title, body: content]
    }
}
=======
class ArticleController
{
	def index() 
	{
		def articles = Article.list()
		[articles:articles]
	}
}
>>>>>>> 3ee12a969d1b3261e4309c13ffbfd458d9843e82
