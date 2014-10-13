import miki.Repository

class BootStrap
{
	def init = { servletContext ->
		if ( Repository.count() == 0 )
		{
			if ( System.properties['os.name'].toLowerCase().contains( "windows" ) )
			{
				new Repository( location: "P:/GitHub/articles" ).save()
			}
			else if ( System.properties['os.name'].toLowerCase().contains( "mac" ) )
			{
				new Repository( location: "/Users/pevargas90/Documents/Journal" ).save()
			}
			else
			{
				new Repository( location: "/home/pevargas90/journal" ).save()
			}
		}
	}
	def destroy = {
	}
}
