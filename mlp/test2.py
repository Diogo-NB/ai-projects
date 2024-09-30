import numpy as np
import matplotlib.pyplot as plt
from mlp import MLP

lower_limit = -1.75
upper_limit = +1.75
input_size = 100
hidden_layers_sizes = [100, 100]

fn = lambda x: np.sin(x) ** 2

x = np.linspace(lower_limit, upper_limit, input_size)
t = fn(x)

mlp = MLP(layers_sizes=[len(x), *hidden_layers_sizes, len(t)], learning_rate=0.0009, tolerated_error=1e-4)

total_error, epochs = mlp.train(inputs=[x], targets=[t])

print(total_error, epochs)

y_test = mlp.predict(x)

fig, ax = plt.subplots()           
ax.plot(x, t)  
ax.plot(x, y_test)  
plt.show()        
