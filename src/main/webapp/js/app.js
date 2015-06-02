'use strict';
var app = angular.module('libraryManagementApp', ['ngMaterial','ngMdIcons','ngResource','angular-loading-bar', 'ngAnimate','reCAPTCHA']);

app.config(['cfpLoadingBarProvider', function(cfpLoadingBarProvider) {
  cfpLoadingBarProvider.includeSpinner = true;

  
}])

app.config(function (reCAPTCHAProvider) {
            // required: please use your own key :)
            reCAPTCHAProvider.setPublicKey('6Lf3wgcTAAAAAOsHQVbAeZGBFemm3BMLj4SFx_oD');

            // optional: gets passed into the Recaptcha.create call
            reCAPTCHAProvider.setOptions({
              theme: 'clean'
            });
})
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








