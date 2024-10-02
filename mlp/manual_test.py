import numpy as np
from numpy import ndarray

x = np.array([[1, 0.5, -1], [0, 0.5, 1], [1, -0.5, -1]], dtype=np.float64)

t = np.array([[1, -1, -1], [-1, 1, -1], [-1, -1, 1]], dtype=np.float64)

# w = np.random.uniform(-0.1, +0.1, (3, 2))
# b = np.random.uniform(-0.1, +0.1, 2)

# predict 

#First layer connection input_size = 3, output_size = 2

v = np.array([[0.12, -0.03], [-0.04, 0.15], [0.31, -0.41]], dtype=np.float64)
v0 = np.array([-0.09, 0.18], dtype=np.float64)

zin = np.dot(x, v) + v0

z = np.tanh(zin)

print(f"First layer connection output:\n{z=}")

#Second layer connection input_size = 2, output_size = 3

w = np.array([[-0.05, -0.34, 0.21], [0.19, -0.9, 0.26]], dtype=np.float64)
w0 = np.array([0.18, -0.27, -0.12], dtype=np.float64)

yin = np.dot(z, w) + w0

y = np.tanh(yin)

print(f"Second layer connection output:\n{y=}")

# training

errototal = 0 + 0.5 * np.sum((t - y) ** 2)

# backpropagation

delta = (t - y) * (1 - y) * (1 + y)

print(f"Delta:\n{delta=}")
print(f"y:\n{z=}")

# update weights and biases

# learning rate
alpha = 0.01

delta_w = alpha * np.dot(z.T, delta) 
delta_w0 = alpha * np.sum(delta, axis=0)

w += delta_w
w0 += delta_w0

delta = np.dot(delta, w.T) * (1 - z) * (1 + z)

delta_v = alpha * np.dot(x.T, delta) 
delta_v0 = alpha * np.sum(delta, axis=0)

v += delta_v
v0 += delta_v0

print(f"Updated weights and biases:\n{v=}\n{v0=}\n{w=}\n{w0=}")
print(f"Error: {errototal}, epochs: {1}")