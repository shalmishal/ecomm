'use strict';
console.log(" start of blogController...")
app
		.controller(
				'BlogController',
				[
						'$scope',
						'BlogService',
						'$location',
						'$rootScope',
						
						'$http',
						function($scope, BlogService, $location, $rootScope,
								$http) {
							console.log("BlogController...")
							var self = this;
							self.Blog = {
								userid : '',
								id : '',
								title: '',
								description: '',
								status : '',
								reason : '',
								errorCode : '',
								errorMessage : ''
							};
							self.blogs = [];
                         self.getSelectedBlog = getBlog
							
							function getBlog(id){
								console.log("selected blog" +id)
								BlogService.getBlog(id)
								.then(
								function(d){
									self.blog = d;
									$location.path('/view_blog');
								},
								function(errResponse){
									console.error('error while fetching blogs');
								}
							
							);
								
								
							};
							self.fetchAllBlogs = function() {
								console.log("fetchAllBlogs...")
								BlogService
										.fetchAllBlogs()
										.then(
												function(d) {
													self.blogs = d;
												},
												function(errResponse) {
													console
															.error('Error while fetching Users');
												});
							};
							
							//self.fatchAllUsers();

							self.createBlog = function(Blog) {
								console.log("createUser...")
								BlogService
										.createBlog(Blog)
										.then(
												self.fetchAllUsers,
												function(errResponse) {
													console
															.error('Error while creating Blog.');
												});
							};
							
							
							self.submit = function() {
								{
									console.log('Saving New User', self.Blog);
									self.createBlog(self.Blog);
								}
								self.reset();
							};

							self.reset = function() {
								self.Blog = {
										userid : '',
										id : '',
										title: '',
										description: '',
										status : '',
										reason : '',
										errorCode : '',
										errorMessage : ''
					};
								$scope.myForm.$setPristine(); // reset Form
							};
							self.fetchAllBlogs();
							 
						} ]);