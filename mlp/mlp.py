import numpy as np
from numpy import ndarray
import matplotlib.pyplot as plt
from typing import Literal

# setting random seed for reproducibility
np.random.seed(42)

class MLP:

    class Layer:
        random_init_limits = (-0.25, +0.25)

        def __init__(self, n_inputs: int, n_outputs: int):
            lower_bound, upper_bound = self.random_init_limits

            self.w = np.random.uniform(lower_bound, upper_bound, (n_inputs, n_outputs))                
            self.b = np.random.uniform(lower_bound, upper_bound, n_outputs)

            self.z : ndarray = np.array([[]])
    
    def __init__(self, layers_sizes: list[int], activation : Literal["tanh", "softmax"] = "tanh" ):
        layers: list[MLP.Layer] = []

        for i in range(len(layers_sizes) - 1):
            mlp_layer_connection = MLP.Layer(layers_sizes[i], layers_sizes[i + 1])
            layers.append(mlp_layer_connection)

        self.layers = layers

        if activation == "tanh":
            self.act_fn = lambda x: np.tanh(x)
            self.d_act_fn = lambda x: (1 + x) * (1 - x)
        elif activation == "softmax":
            self.act_fn = lambda x: np.exp(x) / np.sum(np.exp(x), axis=0)
            self.d_act_fn = lambda x: x * (1 - x)

    def print_parameters(self):
        for i, layer in enumerate(self.layers):
            print(f'Layer {i + 1}')
            print(f'Weights: {layer.w}')
            print(f'Biases: {layer.b}')
            print()
    
    def forward(self, x: ndarray) -> ndarray:
        layers = self.layers
        z = x
        for layer in layers:
            zin = np.dot(z, layer.w) + layer.b
            z = self.act_fn(zin)
            layer.z = z
            
        return z
    
    def backward(self, x: ndarray, y: ndarray, output: ndarray, alpha) -> None:
        g = output - y

        for i in range(len(self.layers) -1, 0, -1):
            self.layers[i].w -= alpha * np.dot(self.layers[i - 1].z.T, g)
            self.layers[i].b -= alpha * np.sum(g, axis=0)

            g = np.dot(g, self.layers[i].w.T) * self.d_act_fn(self.layers[i - 1].z)
        
        # self.layers[2].w -= alpha * np.dot(self.layers[1].z.T, g)
        # self.layers[2].b -= alpha * np.sum(g, axis=0) 
        # 0.0013497964524862751 -> 0.0013470893762627897

        # g = np.dot(g, self.layers[2].w.T) * (1 + self.layers[1].z) * (1 - self.layers[1].z)

        # self.layers[1].w -= alpha * np.dot(self.layers[0].z.T, g)
        # self.layers[1].b -= alpha * np.sum(g, axis=0)

        # g = np.dot(g, self.layers[1].w.T) * (1 + self.layers[0].z) * (1 - self.layers[0].z)
        
        self.layers[0].w -= alpha * np.dot(x.T, g)
        self.layers[0].b -= alpha * np.sum(g, axis=0) 

    def train(self, x: ndarray, y: ndarray, learning_rate = 0.01, tolerated_error = 1e-8, max_epochs = 100000):
        epoch = 0
        error = float('inf')
        while error > tolerated_error and epoch < max_epochs:
            epoch += 1
            output = self.forward(x)
            self.backward(x, y, output, learning_rate)
            error = 0.5 * np.sum((output - y) ** 2)
            if epoch % 5000 == 0:
                print(f'Epoch {epoch}, Error: {error}')

    def predict(self, x):
        return self.forward(x)
                      
x = np.linspace(0, 2 * np.pi, 100)
t = np.sin(x)

x = x.reshape((-1, 1))
t = t.reshape((-1, 1))

mlp = MLP([1, 50, 50, 1], activation="tanh")

mlp.train(x, t, learning_rate=0.001)

y = mlp.predict(x)

plt.plot(x, t, color='blue', label='target')
plt.plot(x, y, color='green', label='predicted')
plt.show()