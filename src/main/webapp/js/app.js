'use strict';
var app = angular.module('libraryManagementApp', ['ngMaterial','ngMdIcons','angular-loading-bar', 'ngAnimate','vcRecaptcha']);

app.config(['cfpLoadingBarProvider', function(cfpLoadingBarProvider) {
  cfpLoadingBarProvider.includeSpinner = true;

  
}])


app.controller('MainCtrl', function($rootScope, $scope, $mdDialog, $mdToast, $animate, $http, $timeout, $q, $log) {

  // Initialize the scope variables
  $scope.info = {
    origin: {},
    maxfare: {},
    returndate: new Date(),
    departuredate: new Date()
  };

  






}

);








