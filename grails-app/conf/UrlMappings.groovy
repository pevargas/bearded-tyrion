class UrlMappings
{

	static mappings = 
	{
        //"/$controller/$action?/$id?(.${format})?"{
        "/$controller/$action?/$file?"
        {
            constraints
            {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
