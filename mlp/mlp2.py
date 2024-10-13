import numpy as np
from numpy import ndarray
import matplotlib.pyplot as plt

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
    
    def __init__(self, layers_sizes: list[int]):
        layers: list[MLP.Layer] = []

        for i in range(len(layers_sizes) - 1):
            mlp_layer_connection = MLP.Layer(layers_sizes[i], layers_sizes[i + 1])
            layers.append(mlp_layer_connection)

        self.layers = layers

    def print_weigths(self):
        for i, layer in enumerate(self.layers):
            print(f'Layer {i + 1}')
            print(f'Weights: {layer.w}')
            print(f'Biases: {layer.b}')
            print()

    def act_fn(self, x) -> ndarray:
        return np.tanh(x)
    
    def forward(self, x: ndarray) -> ndarray:
        layers = self.layers
        z = x
        for layer in layers:
            zin = np.dot(z, layer.w) + layer.b
            z = self.act_fn(zin)
            layer.z = z
            
        return z
    
    def backward(self, x: ndarray, y: ndarray, output: ndarray, alpha: np.float64) -> None:
        g = output - y
        
        # self.layers[2].w -= alpha * np.dot(self.layers[1].z.T, g)
        # self.layers[2].b -= alpha * np.sum(g, axis=0)

        # g = np.dot(g, self.layers[2].w.T) * self.layers[1].z * (1 - self.layers[1].z)

        self.layers[1].w -= alpha * np.dot(self.layers[0].z.T, g)
        self.layers[1].b -= alpha * np.sum(g, axis=0)

        g = np.dot(g, self.layers[1].w.T) * self.layers[0].z * (1 - self.layers[0].z)
        
        self.layers[0].w -= alpha * np.dot(x.T, g)
        self.layers[0].b -= alpha * np.sum(g, axis=0) 

    def train(self, x: ndarray, y: ndarray, learning_rate = 0.1, max_epochs = 100000):
        for epoch in range(max_epochs):
            output = self.forward(x)
            self.backward(x, y, output, learning_rate)
            if (epoch+1) % 1000 == 0:
                loss = -np.sum(y * np.log(output)) / x.shape[0]
                print(f'Epoch {epoch+1}, Loss: {loss:.4f}')

    def predict(self, x):
        return self.forward(x)
                      
x = np.linspace(0, 1, 10)
t = np.sin(x)

x = x.reshape((-1, 1))
t = t.reshape((-1, 1))

mlp = MLP([1, 50, 1])

mlp.train(x, t)

y = mlp.predict(x)

plt.plot(x, t, color='blue', label='target')
plt.plot(x, y, color='green', label='predicted')
plt.show()