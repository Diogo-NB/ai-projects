import numpy as np
from numpy import ndarray

class MLP:

    class LayerConnection:
        random_init_limits = (-0.1, +0.1)

        def __init__(self, n_inputs: int, n_outputs: int, initial_state : tuple[ndarray, ndarray] = None):
            lower_bound, upper_bound = self.random_init_limits

            if initial_state is None:
                self.w = np.random.uniform(lower_bound, upper_bound, (n_inputs, n_outputs))                
                self.b = np.random.uniform(lower_bound, upper_bound, n_outputs)
            else:
                initial_weigths, initial_biases = initial_state
                self.w = initial_weigths              
                self.b = initial_biases

            self.last_input = None

        def test(self, x: ndarray):
            self.last_input = x
            zin = np.dot(x, self.w) + self.b
            z = np.tanh(zin)
            return z          
        
        def update_weigths_and_biases(self, delta: ndarray, alpha: float):
            z = self.last_input

            delta_w = alpha * np.outer(z, delta)
            delta_b = alpha * delta

            self.w += delta_w
            self.b += delta_b
        
        def __str__(self):
            return f"MlpLayerConnections({self.w}, {self.b})"
    
    def __init__(self, layers_sizes: list[int], learning_rate: float = 0.01, tolerated_error: float = 0.005, max_epochs: int = 100000, initial_state : list[tuple[ndarray, ndarray]] = None):
        self.alpha = learning_rate
        self.tolerated_error = tolerated_error
        self.max_epochs = max_epochs
        self.n_layers = len(layers_sizes)

        layers_connections: list[MLP.LayerConnection] = []

        if initial_state is None:
            for i in range(self.n_layers - 1):
                mlp_layer_connection = MLP.LayerConnection(layers_sizes[i], layers_sizes[i + 1])
                layers_connections.append(mlp_layer_connection)
        else:
            for i in range(self.n_layers - 1):
                mlp_layer_connection = MLP.LayerConnection(layers_sizes[i], layers_sizes[i + 1], initial_state=initial_state[i])
                layers_connections.append(mlp_layer_connection)          

        self.layers_connections = layers_connections

    def train(self, inputs: ndarray, targets: ndarray):
        alpha, max_epochs, tolerated_error = self.alpha, self.max_epochs, self.tolerated_error
        layers_connections = self.layers_connections
        epochs = 0
        total_error = float('inf')

        while total_error > tolerated_error and epochs < max_epochs:
            epochs += 1
            total_error = 0.0

            for i in range(inputs.shape[0]):
                x = inputs[i]
                t = targets[i]
                z = x

                y = self.predict(x)

                dif = t - y
                total_error += 0.5 * np.sum(dif ** 2)


                # Backward propagation
                delta = dif * (1 + y) * (1 - y)

                for layer_conn in reversed(layers_connections):
                    w = layer_conn.w
                    z = layer_conn.last_input

                    new_delta = np.dot(delta, w.T) * (1 + z) * (1 - z)
                                        
                    layer_conn.update_weigths_and_biases(delta, alpha)

                    delta = new_delta

            if epochs % 200 == 0: print(f"{epochs=} , {total_error=}")  
        return total_error, epochs
    
    def predict(self, x:ndarray) -> ndarray:
        layers_connections = self.layers_connections

        for layer_conn in layers_connections:
            z = layer_conn.test(x)
            x = z
        y = z
        return y