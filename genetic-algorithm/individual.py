class Individual:

    def __init__(self, value):
        self.value = value

    def fitness(self) -> float:
        raise NotImplementedError

    def crossover(self, other) -> tuple:
        raise NotImplementedError
    
    def mutate(self) -> None:
        raise NotImplementedError
    