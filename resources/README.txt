In our design we used the visitor pattern and function objects a lot in order to achieve do what
was asked. We originally started with a Cell interface and a bunch of classes representing each
type of value a cell could contain. We eventually realized this was not practical to the design and
ended up using one Cell class that implements a general interface for worksheet cells. Then using
the parser that was given to us and the Sexp visitor pattern, we were able to implement the
functions for the spreadsheet. The extra function we made was a concat function that will take an
Sexp and convert it to string and then add it to an output string with all the other arguments
given to the concat function. Our model ended up being much simpler in terms of the number of
classes than we originally had.

Our textual view consists of rendering the model to an appendable, which in the main method,
writes that appendable to a file. The GUI view creates a table of cells, with the headers on
the top and left side. Each cell that contains a value holds the evaluation of that particular
cell in the view. It creates a view that is at least the size of the furthest out cell
that contains non-blank contents in both directions. It is able to scroll in both directions
using the scroll bars on the sides of the frame. It is also able to handle a mouse-click
that selects the cell that is clicked on. This will be useful when dealing with controllers
and editing contents of cells.

Our controller takes in a model that is an IWorksheet and a view which is an IWorksheetView.
It contains methods that would result in altering the model in anyway and changing the state.
These methods are controlled by clicking either of the two buttons on the top to select changes
or revert changes, or by clicking specific key inputs. The user is able to move the selected
cell through the use of arrow keys and change the contents of a cell using the given text box above.

The only big changes we made since the last assignment were adding methods to interfaces in order
to give us more access to information across all classes and to be able to do new things with
our previously created classes. No other major changes were made to the design or the code.