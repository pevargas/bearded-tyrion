package miki

class ArticleService
{
   def fullPath( file )
   {
      def title = file
      if ( ! ( title =~ /\.md/ ) )
      {
         title += ".md"
      }
      title = title.replaceAll( "\\s", "_" )
      def repo = Repository.list( )[0].location
      def filename = repo + "/" + title
      return filename
   }
}