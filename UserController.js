'use strict';
console.log(" start of UserController...")
app
		.controller(
				'UserController',
				[
						'$scope',
						'UserService',
						'$location',
						'$rootScope',
					
						'$http',
						function($scope, UserService, $location, $rootScope, $http) {
							console.log("UserController...")
							var self = this;
							self.UserDetails = {
								userid : '',
								username : '',
								password : '',
								contact : '',
								address : '',
								email : '',
								is_online : '',
								Role : '',
								errorcode : '',
								errormessage : ''
							};
							self.users = [];

							self.fetchAllUsers = function() {
								console.log("fetchAllUsers...")
								UserService
										.fetchAllUsers()
										.then(
												function(d) {
													self.users = d;
													alert("Registered Successfully")
													$location.path('/Login')
												},
												function(errResponse) {
													console
															.error('Error while fetching Users');
												});
							};
							
							//self.fatchAllUsers();

							self.createUser = function(UserDetails) {
								console.log("createUser...")
								UserService
										.createUser(UserDetails)
										.then(
											
												self.fetchAllUsers,
												
												function(errResponse) {
													console
															.error('Error while creating Userdetails.');
												});
							};
							
							self.myProfile = function() {
								console.log("myProfile...")
								UserService
										.myProfile()
										.then(
												function(d) {
													self.UserDetails = d;
													$location.path("/myProfile")
												},
												function(errResponse) {
													console
															.error('Error while fetch profile.');
												});
							};

							self.updateUser = function(UserDetails, userid) {
								console.log("updateUser...")
								UserService
										.updateUser(UserDetails, userid)
										.then(
												self.fetchAllUsers,
												function(errResponse) {
													console
															.error('Error while updating User.');
												});
							};

							self.authenticate = function(UserDetails) {
								console.log("authenticate...")
								UserService
										.authenticate(UserDetails)
										.then(

												function(d) {

													self.UserDetails = d;
													console
															.log("UserDetails.errorcode: "
																	+ self.UserDetails.errorcode)
																	
													if (self.UserDetails.errorcode =="404")

													{
														alert("Invalid user..Enter correct userid and password")

														self.UserDetails.userid = "";
														self.UserDetails.password = "";
													

													} else {
														console
																.log("Valid credentials. Navigating to home page")
																$rootScope.currentUser = self.Userdetails
																$http.defaults.headers.common['Authorization'] = 'Basic '
																	+ $rootScope.currentUser;
															$cookieStore
																	.put(
																			'currentUser',
																			$rootScope.currentUser);
															$location.path('/');

													}

												},
												function(errResponse) {

													console
															.error('Error while authenticate Users');
												});
							};

							self.logout = function() {
								console.log("logout")
								$rootScope.currentUser = {};
								$cookieStore.remove('currentUser');
								UserService.logout()
								$location.path('/');

							}

							self.deleteUser = function(userid) {
								console.log("UserController...")
								UserService
										.deleteUser(userid)
										.then(
												self.fetchAllUsers,
												function(errResponse) {
													console
															.error('Error while deleting User.');
												});
							};

							// self.fetchAllUsers();

							self.login = function() {
								{
									console.log('login validation????????',
											self.UserDetails);
									self.authenticate(self.UserDetails);
								}

							};

							self.submit = function() {
								{
									console.log('Saving New User', self.UserDetails);
									self.createUser(self.UserDetails);
								}
								self.reset();
							};

							self.reset = function() {
								self.UserDetails = {
									userid : '',
									username : '',
									password : '',
									contact : '',
									address : '',
									email : '',
									is_online : '',
									errorcode : '',
									errormessage : ''
								};
								$scope.myForm.$setPristine(); // reset Form
							};

						} ]);