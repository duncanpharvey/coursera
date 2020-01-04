# Coursera

### [Algorithms - Part I](https://www.coursera.org/learn/algorithms-part1/home/welcome)

#### Percolation

Models a percolation system using an n-by-n grid of sites. A system percolates if we fill all open sites connected to the top row 
and that process fills some open site on the bottom row.

If sites are independently set to be open with probability p, what is the probability that the system percolates?
Performs a Monte Carlo simulation to determine the threshold p* above which a system always percolates for sufficiently large N.

[Specification](https://coursera.cs.princeton.edu/algs4/assignments/percolation/specification.php)

[Solution](https://github.com/duncanpharvey/coursera/tree/master/Percolation)

Notes
- Solution currently does not account for backwash - sites connected to the bottom row are filled even though they are not connected
to the top row

#### Deque

Custom implementations of a Deque (double ended queue) and a Randomized Queue using a doubly linked list and array, respectively.
Uses a Randomized Queue to return a random permutation of strings from an inputted list.

[Specification](https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php)

[Solution](https://github.com/duncanpharvey/coursera/tree/master/Deque)

#### Collinear Points

Computational Geometry problem in finding the line segments that connect a subset of 4 or more points in a plane. Solved with two different algorithms:
- Brute force algorithm with a time complexity O(n<sup>4</sup>)
- Fast algorithm that uses sorting to achieve time complexity of O(n<sup>2</sup>log<sub>2</sub>n)

[Specification](https://coursera.cs.princeton.edu/algs4/assignments/collinear/specification.php)

[Solution](https://github.com/duncanpharvey/coursera/tree/master/CollinearPoints)
