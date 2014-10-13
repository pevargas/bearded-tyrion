package miki

class ArticleService
{
   def createDate = "miki.dateCreated="
   def updateDate = "miki.lastUpdated="
   def tagList    = "miki.tags="

   // Return the hard-coded directory to store the articles
   def directory( )
   {
      def repo = Repository.list( )[0].location
      return repo
   }

   // Convert the filename to something more legible
   def humanTitle( filename )
   {
      def human = filename.replaceAll( "_", " " );
      human = human.replaceAll( ".md", "" )
      return human
   }

   // Convert the file name to a full path
   def fullPath( file )
   {
      def title = file
      if ( ! ( title =~ /\.md/ ) )
      {
         title += ".md"
      }
      title = title.replaceAll( "\\s", "_" )
      def filename = directory( ) + "/" + title
      return filename
   }

   // Open the file and separate the meta data from the content
   def parseFile( filename )
   {
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

   // Write the content to a new file
   def writeFile( filename, data )
   {
      new File( filename ).withWriter( )
        {
            file ->
            file.writeLine( createDate + data.created )
            file.writeLine( updateDate + new Date().format("yyyy/MM/dd HH:mm:ss") )
            file.writeLine( tagList + data.tags )
            file.writeLine( data.content )
        }
   }
}