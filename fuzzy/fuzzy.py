import numpy as np
from matplotlib import pyplot as plt
# construir equações da retas

fig, axs = plt.subplots(4, 1)

# Pressão
# Baixa
y1_a = lambda x: (x / 12.5 ) * (x < 12.5)
y2_a = lambda x: (- x / 12.5 + 2) * (x >= 12.5) * (x <= 25)

y_a = lambda x: y1_a(x) + y2_a(x)

x_a = np.linspace(0, 25)
t_a = y_a(x_a)

axs[0].plot(x_a, t_a)

# Alta
y1_b = lambda x: (x / 20 - 0.75) * (x >= 15) * (x < 35)
y2_b = lambda x: (- x / 20 + 2.75) * (x >= 35) * (x <= 55)

y_b = lambda x: y1_b(x) + y2_b(x)

x_b = np.linspace(15, 55)
t_b = y_b(x_b)

axs[0].plot(x_b, t_b)
axs[0].set_title("Pressão")
axs[0].legend(["Baixa", "Alta"])

# Temperatura
y1_c = lambda x: (x / 50)  * (x < 50)
y2_c = lambda x: (- x / 50 + 2)  * (x >= 50) * (x <= 100)

y_c = lambda x: y1_c(x) + y2_c(x)

x_c = np.linspace(0, 100)
t_c = y_c(x_c)

axs[1].plot(x_c, t_c)

y1_d = lambda x: (x / 65 - 14/13) * (x >= 70) * (x < 135)
y2_d = lambda x: (- x / 65 + 40/13) * (x >= 135) * (x <= 200)

y_d = lambda x: y1_d(x) + y2_d(x)

x_d = np.linspace(70, 200)
t_d = y_d(x_d)

axs[1].plot(x_d, t_d)
axs[1].set_title("Temperatura")
axs[1].legend(["Baixa", "Alta"])

# Saída em vazão
y1_e = lambda x: (x / 125) * (x < 125)
y2_e = lambda x: (- x / 125 + 2) * (x >= 125) * (x <= 250)

y_e = lambda x: y1_e(x) + y2_e(x)

x_e = np.linspace(0, 250)
t_e = y_e(x_e)

axs[2].plot(x_e, t_e)

y1_f = lambda x: (x / 175 - 6 / 7) * (x >= 150) * (x < 325)
y2_f = lambda x: (- x / 175 + 20 / 7) * (x >= 325) * (x <= 500)

y_f = lambda x: y1_f(x) + y2_f(x)

x_f = np.linspace(150, 500)
t_f = y_f(x_f)

axs[2].plot(x_f, t_f)
axs[2].set_title("Vazão")
axs[2].legend(["Baixa", "Alta"])

get_min_pressao = lambda x: np.min([y_a(x), y_b(x)])
get_min_temp = lambda x: np.min([y_c(x), y_d(x)])
get_min_vazao = lambda x: np.min([y_e(x), y_f(x)])

P, T = 20, 100

# Regras
# 1. Se pressão é baixa e temperatura é baixa, então vazão é baixa
r1 = np.min([y_a(P), y_c(T)])
# 2. Se pressão é baixa e temperatura é alta, então vazão é alta
r2 = np.min([y_a(P), y_c(T)])
# 3. Se pressão é alta e temperatura é baixa, então vazão é alta
r3 = np.min([y_b(P), y_c(T)])
# 4. Se pressão é alta e temperatura é alta, então vazão é baixa
r4 = np.min([y_b(P), y_d(T)])

# Vazão - baixa
s = np.max([r1, r4])

# Vazão - alta
s = np.max([r2, r3])

# Defuzzificação







plt.tight_layout()
plt.show()