import numpy as np

def roulette_wheel_selection(f, pop, size):
    f_sum = np.sum(f)
    prob = f / f_sum
    selection = np.random.choice(pop, size, p=prob)
    return selection

def __tournament_selection__(f, pop, size, k):
    pop_size = len(pop)
    for i in range(size):
        indices = np.random.choice(pop_size, k)
        winner = roulette_wheel_selection(f[indices], pop[indices], 1)[0]
        yield winner  

def tournament_selection(f, pop, size, k):
    return list(__tournament_selection__(f, pop, size, k))

class SelectionMethod:

    def __init__(self, size):
        self.size = size

    def select(self, f, pop):
        raise NotImplementedError
    
class RouletteWheel(SelectionMethod):

    def __init__(self, size):
        super().__init__(size)
    
    def select(self, f, pop):
        return roulette_wheel_selection(f, pop, self.size)
    
    def __str__(self):
        return f"RouletteWheel: size: {self.size}"
    
class Tournament(SelectionMethod):
        
    def __init__(self, size, k):
        super().__init__(size)
        self.k = k
    
    def select(self, f, pop):
        return np.array(tournament_selection(f, pop, self.size, self.k))
    
    def __str__(self):
        return f"Tournament: size: {self.size}, k: {self.k}"