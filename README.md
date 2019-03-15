# The Parking House
## Exercise:
This exercise can be solved by using 2 independent programs (i.e. communicating
through a socket) or 2 threads in the same program. Solve it both ways.

Construct a client - server simulation of a parking house with a limited number of
parking spaces, say f.ex. 10.

The client delivers numbered cars (i.e. they are objects), which arrives and later
departs randomly to/from the parking house. Did anyone say “Factory Pattern” ?
The server is the parking house. It must take care of the cars as long as they are in
the house, and reject new arrivals, when filled.

Both client and server must send a message to show their status.

## Solution:
### Client/Server simulation
.../test/socket/_Client

.../test/socket/_Server
### Multithread
.../test/thread/_Thread
