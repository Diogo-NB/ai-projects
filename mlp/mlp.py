import numpy as np
from numpy import ndarray

class MLP:

    class LayerConnection:
        random_init_limits = (-0.1, +0.1)

        def __init__(self, n_inputs: int, n_outputs: int):
            lower_bound, upper_bound = self.random_init_limits

            self.w = np.random.uniform(lower_bound, upper_bound, (n_inputs, n_outputs))                
            self.b = np.random.uniform(lower_bound, upper_bound, n_outputs)      
    
    def __init__(self, layers_sizes: list[int], learning_rate: float = 0.01, tolerated_error: float = 0.005, max_epochs: int = 100000):
        self.learning_rate = learning_rate
        self.tolerated_error = tolerated_error
        self.max_epochs = max_epochs
        self.n_layers = len(layers_sizes)

        layers_connections: list[MLP.LayerConnection] = []

        for i in range(self.n_layers - 1):
            mlp_layer_connection = MLP.LayerConnection(layers_sizes[i], layers_sizes[i + 1])
            layers_connections.append(mlp_layer_connection)

        self.layers_connections = layers_connections

    def train(self, inputs: ndarray, targets: ndarray):
        learning_rate, max_epochs, tolerated_error, layers_connections = self.learning_rate, self.max_epochs, self.tolerated_error, self.layers_connections
        epochs = 0
        alpha = learning_rate
        total_error = float('inf')
        x = inputs
        t = targets

        while total_error > tolerated_error and epochs < max_epochs:
            epochs += 1
            total_error = 0.0

            z_list : list[ndarray] = [x]
            for layer_conn in layers_connections:
                w = layer_conn.w
                b = layer_conn.b
                zin = np.dot(z_list[-1], w) + b
                z = np.tanh(zin) # activation function
                z_list.append(z)

            y = z

            dif = t - y

            total_error += 0.5 * np.sum(dif ** 2)

            g = dif * (1 - y) * (1 + y)

            # backpropagation
            for i in range(len(layers_connections) - 1, -1, -1):
                layer_conn = layers_connections[i]
                z = z_list[i]

                if i > 0:
                    new_g = np.dot(g, layer_conn.w.T) * (1 - z) * (1 + z)

                g_w = alpha * np.dot(z.T, g) 
                g_b = alpha * np.sum(g, axis=0)
                layer_conn.w += g_w
                layer_conn.b += g_b

                g = new_g
            
            if epochs % 1000 == 0: print(f"{epochs=} , {total_error=}, {alpha=}")

        return total_error, epochs
    
    def predict(self, inputs: ndarray):
        x = inputs
        layers_connections = self.layers_connections

        z = x
        for layer_conn in layers_connections:
            w = layer_conn.w
            b = layer_conn.b
            zin = np.dot(z, w) + b
            z = np.tanh(zin)

        return z