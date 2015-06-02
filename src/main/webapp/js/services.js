app.factory('bookFactory', ['$http', function($http) {

	var urlBase = '/rest/book';
	var bookFactory = {};

	bookFactory.getBooks = function () {
		return $http.get(urlBase+'/');
	};

	bookFactory.getBookById = function (bookId) {
		return $http.get(urlBase + '/' + bookId);
	};

	bookFactory.insertBook = function (book) {
		return $http.post(urlBase+'/save', book);
	};

	bookFactory.updateBook = function (book) {
		return $http.put(urlBase + '/update/' + book.bookId, book)
	};

	bookFactory.deleteBookbyId = function (bookId) {
		return $http.delete(urlBase + '/delete/' + bookId);
	};

   bookFactory.validate = function (resp) {
		return $http.post(urlBase + '/validate',resp);
	};

	return bookFactory;
}]);