class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/" (controller:'home', action:'home', method:'GET')
        "500"(view:'/error')
	}
}
