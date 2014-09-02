<<<<<<< HEAD
import miki.Repository

class BootStrap {
=======
import miki.Article
>>>>>>> 3ee12a969d1b3261e4309c13ffbfd458d9843e82

class BootStrap
{
    def init = { servletContext ->
<<<<<<< HEAD
      if ( Repository.count() == 0 )
      {
        new Repository( location: "/Users/pevargas90/Documents/Journal" ).save()
      }
=======
    	if ( Article.count() == 0 )
    	{
    		def a1 = new Article(title: 'Corbridge Lion', created: '19:51, 22 January 2007', updated: '07:21, 21 March 2014', content: 'The Corbridge Lion, Northumberland, England, is an ancient Roman free-standing sandstone sculpture of a male lion standing on a prone animal (possibly a deer) on a semi-cylindrical coping stone base.').save()
    		def a2 = new Article(title: 'Kid Wikkid', created: '00:06, 23 February 2014', updated: '23:14, 11 July 2014', content: 'Kid Wikkid is a Canadian glam metal band that existed from 1983-1985.' ).save()
    		def a3 = new Article(title: 'Marta Luna Iriarte', created: '19:58, 27 October 2011', updated: '23:22, 5 November 2013', content: 'Marta Luna Iriarte is a Spanish football defender, currently plying for Levante UD in Spain\'s Primera División.[1] She has also played in the NCAA for Valparaiso Crusaders.').save()    		
    	}
>>>>>>> 3ee12a969d1b3261e4309c13ffbfd458d9843e82
    }
    def destroy = {
    }
}
