app.controller('bookController', ['$mdToast','$mdDialog','$scope', 'bookFactory', 
    function ($mdToast,$mdDialog,$scope, bookFactory) {

        $scope.status;
        $scope.books;
        getBooks();
    //$rootScope.books = getBooks();

    
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
          '<form name="addingForm" role="form" novalidate>'+
          '<md-input-container flex>'+
          '<label>Book Name</label>'+
          '<input type="text" ng-model="booktmp2.bookName" name="bookName" required />'+
          '</md-input-container>'+
          '<md-input-container flex>'+
          '<label>Author Name</label>'+
          '<input type="text" ng-model="booktmp2.authorName" name="authorName" required />'+
          '</md-input-container>'+ 
          '<div re-captcha ng-model="user.captcha"></div>'+
          '<md-button class="md-raised md-primary" ng-click="closeDialog()" class="md-primary">'+
          'Cancel'+
          '</md-button>'+
          '<md-button type="submit" ng-click="submit(booktmp2)" class="md-raised md-primary" ng-disabled="addingForm.$invalid" class="md-primary">'+
          'Add'+
          '</md-button>'+
          '</form>'+
          '  </md-dialog-content>' +
          '</md-dialog>',
          controller: function DialogController($scope, $mdDialog) {

            $scope.submit = function(booktmp2) {
              console.log(booktmp2);
              addBook(booktmp2);
              $scope.booktmp2 = $scope.initial;
              $mdDialog.hide();
          }
          $scope.closeDialog = function() {
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
          '<form>'+
          '<md-input-container flex>'+
          '<label>Book Name</label>'+
          '<input type="text" ng-model="booktmp.bookName" name="bookName" required></input>'+
          '</md-input-container>'+
          '<md-input-container flex>'+
          '<label>Author Name</label>'+
          '<input type="text" ng-model="booktmp.authorName" name="authorName" required />'+
          '</md-input-container>'+ 
          '<md-button class="md-raised md-primary" ng-click="closeDialog()" class="md-primary">'+
          'Cancel'+
          '</md-button>'+
          '<md-button class="md-raised md-primary" ng-click="submit(booktmp)" class="md-primary">'+
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



app.controller('bookEditButtonController', ['$mdDialog','$scope', 'bookFactory', 
    function ($mdDialog,$scope, bookFactory) {




      $scope.editDialog = function(book) {

          $mdDialog.show({
              clickOutsideToClose: true,
          scope: $scope,        // use parent scope in template
          preserveScope: true,  // do not forget this if use parent scope
          // Since GreetingController is instantiated with ControllerAs syntax
          // AND we are passing the parent '$scope' to the dialog, we MUST
          // use 'vm.<xxx>' in the template markup
          template: '<md-dialog>' +
          '  <md-dialog-content>' +
          '     Hi There {{book.bookName}}' +
          '  </md-dialog-content>' +
          '</md-dialog>',
          controller: function DialogController($scope, $mdDialog) {
            $scope.closeDialog = function() {
              $mdDialog.hide();
          }
      }
  });
      };


  }]);

app.controller('bookAddingButtonController', ['$mdDialog','$scope', 'bookFactory', 
    function ($mdDialog,$scope, bookFactory) {







    }]);