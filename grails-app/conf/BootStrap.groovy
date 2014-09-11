import miki.Repository

class BootStrap
{
	def init = { servletContext ->
		if ( Repository.count() == 0 )
		{
			//new Repository( location: "P:/GitHub/articles" ).save()
			new Repository( location: "/Users/pevargas90/Documents/Journal" ).save()
		}
	}
	def destroy = {
	}
}
