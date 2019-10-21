var app = angular.module('bfCodeTest', []);

app.controller('studentCtrl', function($scope, $location, $http) {
	console.log("StudentCtrl loaded.");
	
	$http.get('http://localhost:8082/api/getStudents')
	.then(function(response) {
		$scope.students = response.data;
	});

});