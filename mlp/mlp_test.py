import numpy as np
from mlp import MLP
import matplotlib.pyplot as plt

x = np.linspace(-1, 1, 100)
t = np.sin(np.pi * x)

plt.plot(x, t)
plt.show()

x = x.reshape(-1, 1)
t = t.reshape(-1, 1)

input_size = x.shape[1]
output_size = t.shape[1]

hidden_layers = [50, 50, 50, 50]

mlp = MLP([input_size, *hidden_layers, output_size], learning_rate=0.01)   

error, epochs = mlp.train(x, t)

print(f"Error: {error}, epochs: {epochs}")