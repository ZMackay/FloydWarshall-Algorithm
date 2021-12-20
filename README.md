# FloydWarshall-Algorithm

This code is a implementation of the all-pairs shortest path algorithm, Floyd-Warshall. This program runs in Theta(V^3) time, excluding menial things such as printing the results, and follows a dynamic programming approach. 

Two types of matrices are maintained: the distance matrix and the predecessor matrix. The distance matrix is a representaion of the distance from a node to others for every iteration k. With k representing the amount of edges that are traveled to calculate the value. The predecessor matrix is a map of a source node to destination node's previous node, to finding the target. 
