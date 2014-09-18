package miki

class ArticleService
{
   def createDate = "miki.dateCreated="
   def updateDate = "miki.lastUpdated="
   def tagList    = "miki.tags="

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

   def parseFile( filename )
   {
      //def corpus = ""
      //def created, updated, tags
      def data = [:]
      data.content = ""

      new File( filename ).eachLine
      {
         line -> 
         if ( line =~ createDate )
         {
            data.created = line.substring( createDate.length() )
         }
         else if ( line =~ updateDate )
         {
            data.updated = line.substring( createDate.length() )
         }
         else if ( line =~ tagList )
         {
            data.tags = line.substring( tagList.length() ).tokenize( "," )
         }
         else
         {
            data.content += line + "\r\n"
         }
      }

      return data
   }
}