'use strict';
var app = angular.module('libraryManagementApp', ['ngMaterial','ngMdIcons','angular-loading-bar', 'ngAnimate','vcRecaptcha']);

app.config(['cfpLoadingBarProvider', function(cfpLoadingBarProvider) {
  cfpLoadingBarProvider.includeSpinner = true;
}]);












