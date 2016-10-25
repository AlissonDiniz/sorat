/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alisson
 */
class UrlMappings {
    static mappings = {
        "/$controller/$action?/$id?"{
            constraints {
                // apply constraints here
            }
        }
        
        "/login/$action?"(controller: "login") 
        "/logout/$action?"(controller: "logout")

        "404"(view:"/notFound")
        "500"(view:'/error')
    }
}

