In our design we used the visitor pattern and function objects a lot in order to achieve do what
was asked. We originally started with a Cell interface and a bunch of classes representing each
type of value a cell could contain. We eventually realized this was not practical to the design and
ended up using one Cell class that implements a general interface for worksheet cells. Then using
the parser that was given to us and the Sexp visitor pattern, we were able to implement the
functions for the spreadsheet. The extra function we made was a concat function that will take an
Sexp and convert it to string and then add it to an output string with all the other arguments
given to the concat function. Our model ended up being much simpler in terms of the number of
classes than we originally had.