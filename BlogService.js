'use strict';
 
app.factory('BlogService', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
	
	console.log("BlogService...")
	
	var BASE_URL='http://localhost:8040/ecommerce'
		
    return {
         
            fetchAllBlogs: function() {
            	console.log("calling fetchAllBlogs ")
                    return $http.get(BASE_URL+'/blogs')
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while fetching UserDetailss');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
            getBlog: function(id){
            	console.log("calling get blog in service")
            	return $http.get(BASE_URL+'/blog/' +id)
            	.then(
            	function(response){
            		$rootScope.selectedBlog = response.data
            		return response.data;
            		},
            		function(errResponse)
            		{
            			console.error('error while getting blog');
            			return $q.reject(errResponse);
            		}
            	);
            },	
            
            
             
            createBlog: function(Blog){
            	console.log("calling create user")
                    return $http.post(BASE_URL+'/createblogs/', Blog)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while creating user');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
        
            
           
         
    };
 
}]);