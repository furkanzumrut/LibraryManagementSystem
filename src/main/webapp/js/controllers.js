app.controller('bookController', ['$mdToast','$mdDialog','$scope', 'bookFactory', 
    function ($mdToast,$mdDialog,$scope, bookFactory, vcRecaptchaService) {

        $scope.status;
        $scope.books;
        getBooks();
    //$rootScope.books = getBooks();

    

    $scope.toastPosition = {
        bottom: false,
        top: true,
        left: false,
        right: true
    };
    $scope.getToastPosition = function() {
        return Object.keys($scope.toastPosition)
        .filter(function(pos) { return $scope.toastPosition[pos]; })
        .join(' ');
    };

    function showSimpleToast(message){
        $mdToast.show(
          $mdToast.simple()
          .content(message)
          .position($scope.getToastPosition())
          .hideDelay(1700)
          );
    }

    function getBooks() {
        bookFactory.getBooks()
        .success(function (books) {
            $scope.books = books;
        })
        .error(function (error) {
            $scope.status = 'Unable to load book data: ' + error.message;
            showSimpleToast('An error occurred while getting the books. Try again.');
        });
    }
    function addBook(book) {
        bookFactory.insertBook(book)
        .success(function (books) {
        //getBooks();
        $scope.books.push(book);
        showSimpleToast(book.bookName+' was added successfully.');

    })
        .error(function (error) {
            $scope.status = 'Unable to load add book data: ' + error.message;
            showSimpleToast('An error occurred while adding the book. Try again.');
        });
    }
    function updateBook(book) {
        bookFactory.updateBook(book)
        .success(function (books) {
            getBooks();
        //$scope.books.push(book);
        showSimpleToast(book.bookName+' was updated successfully.');

    })
        .error(function (error) {
            $scope.status = 'Unable to load add book data: ' + error.message;
            showSimpleToast('An error occurred while updating the book. Try again.');
        });
    }
    function deleteBook(book) {
        bookFactory.deleteBookbyId(book.bookId)
        .success(function (books) {
            $scope.books.splice( $scope.books.indexOf(book), 1 );
        //getBooks();
        showSimpleToast(book.bookName+' was deleted successfully.');

    })
        .error(function (error) {
            $scope.status = 'Unable to load book data: ' + error.message;
            showSimpleToast('An error occurred while deleting the book. Try again.');
        });
    }

    $scope.deleteConfirm = function(book) {
     // Appending dialog to document.body to cover sidenav in docs app
     var confirm = $mdDialog.confirm()
     .title('Would you like to delete '+book.bookName+'?')
     .ok('Delete')
     .cancel('Cancel')
     .targetEvent(book);
     $mdDialog.show(confirm).then(function() {

        deleteBook(book);
        
        

        
    }, function() {
      //Nothing
  });
 };

 $scope.addingDialog = function() {

  $mdDialog.show({
      clickOutsideToClose: true,
          scope: $scope,        // use parent scope in template
          preserveScope: true,  // do not forget this if use parent scope
          // Since GreetingController is instantiated with ControllerAs syntax
          // AND we are passing the parent '$scope' to the dialog, we MUST
          // use 'vm.<xxx>' in the template markup
          template: '<md-dialog>' +
          '  <md-dialog-content>' +
          '<form  name="addingForm" role="form" novalidate>'+
          '<md-input-container flex>'+
          '<label>Book Name</label>'+
          '<input type="text" ng-model="booktmp2.bookName" name="bookName" required ng-minlength="2" ng-maxlength="128" />'+
          '</md-input-container>'+
          '<md-input-container flex>'+
          '<label>Author Name</label>'+
          '<input type="text" ng-model="booktmp2.authorName" name="authorName" required ng-minlength="2" ng-maxlength="128" />'+
          '</md-input-container>'+ 
          '<div vc-recaptcha key="\'6Lf3wgcTAAAAAOsHQVbAeZGBFemm3BMLj4SFx_oD\'" on-create="setWidgetId(widgetId)" on-success="setResponse(response)"></div>'+
          '<md-button class="md-raised md-primary" ng-click="closeDialog()" class="md-primary">'+
          'Cancel'+
          '</md-button>'+
          '<md-button type="submit"  ng-click="submit(booktmp2)" class="md-raised md-primary" ng-disabled="addingForm.$invalid" class="md-primary">'+
          'Add'+
          '</md-button>'+
          '</form>'+
          '  </md-dialog-content>' +
          '</md-dialog>',
          controller: function DialogController($scope, $mdDialog,vcRecaptchaService) {

            $scope.response = null;
            $scope.widgetId = null;
             $scope.setResponse = function (response) {
                    console.info('Response available');
                    $scope.response = response;
                };
                $scope.setWidgetId = function (widgetId) {
                    console.info('Created widget ID: %s', widgetId);
                    $scope.widgetId = widgetId;
                };
            $scope.submit = function(booktmp2) {
              var valid;
                    console.log('sending the captcha response to the server', $scope.response);
                    valid = bookFactory.validate($scope.response);
                    if (valid) {
                        console.log('Success');
                        console.log(booktmp2);
                          addBook(booktmp2);
                          $scope.booktmp2 = $scope.initial;
                          $mdDialog.hide();
                    } else {
                        console.log('Failed validation');
                        // In case of a failed validation you need to reload the captcha
                        // because each response can be checked just once
                        vcRecaptchaService.reload($scope.widgetId);
                    }
              
          }
          $scope.closeDialog = function() {
            $scope.booktmp2 = $scope.initial;
              $mdDialog.hide();
          }
      }
  });
};



$scope.editDialog = function(book) {
  $scope.booktmp = angular.copy(book);
  

  $mdDialog.show({
      clickOutsideToClose: true,
          scope: $scope,        // use parent scope in template
          preserveScope: true,  // do not forget this if use parent scope
          // Since GreetingController is instantiated with ControllerAs syntax
          // AND we are passing the parent '$scope' to the dialog, we MUST
          // use 'vm.<xxx>' in the template markup
          template: '<md-dialog>' +
          '  <md-dialog-content>' +
          '<form name="EditingForm" role="form" novalidate>'+
          '<md-input-container flex>'+
          '<label>Book Name</label>'+
          '<input type="text" ng-model="booktmp.bookName" name="bookName" required ng-minlength="2" ng-maxlength="128"></input>'+
          '</md-input-container>'+
          '<md-input-container flex>'+
          '<label>Author Name</label>'+
          '<input type="text" ng-model="booktmp.authorName" name="authorName" required ng-minlength="2" ng-maxlength="128" />'+
          '</md-input-container>'+ 
          '<md-button class="md-raised md-primary" ng-click="closeDialog()" class="md-primary">'+
          'Cancel'+
          '</md-button>'+
          '<md-button type="submit" class="md-raised md-primary" ng-click="submit(booktmp)" ng-disabled="EditingForm.$invalid" class="md-primary">'+
          'Edit'+
          '</md-button>'+
          '</form>'+
          '  </md-dialog-content>' +
          '</md-dialog>',
          controller: function DialogController($scope, $mdDialog) {

            $scope.submit = function(booktmp) {
              console.log(booktmp);
              updateBook(booktmp);
              $scope.book = booktmp;
              $mdDialog.hide();

          }
          $scope.closeDialog = function() {
              $mdDialog.hide();

          }
      }
  });
};

}]);

