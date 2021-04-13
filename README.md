# Scheduling Algorithms
> The project was developed with scheduling algorithms.
> 
## :information_source: Information 

The scheduler is responsible for deciding the order of execution of the finished processes, that is, which schedules the processes. The scheduling of processes is performed by an algorithm that aims to treat efficiently and quickly the processes to be treated. Several criteria can be defined for the evaluation of schedulers. The most frequent are: 

- Execution time (turnaround) → measures the time elapsed between the creation and the end of the task, computing all the processing and waiting times.

- Waiting time → total time lost by the task in the ready queue, waiting for the processor.

- Response time → time elapsed between the arrival of an event in the system and the immediate result of its processing

- Efficiency → Indicates the degree of utilization of the processor in the execution of the user's tasks.

- Justice → Distribution of the processor among the completed tasks.

The implemented algorithms were: RR, FCFS and SJF.

Round-Robin (RR) is a task scheduling algorithm (processes) that consists of dividing the usage time of the CPU (Central Processing Unit). Each process receives a slice of time, this time is called Time-Slice, also known as Quantum. The processes are all stored in a circular Buffer. The scheduler executes each task for the time determined by the Time-Slice and at the end of this period the context change is performed, where the next queue process starts to be executed by the CPU until the Time-Slice period has passed. After going through all the processes in the queue, these activities are repeated and the scheduler points to the first task.


The FCFS algorithm, in its most elementary form of scheduling, consists of simply attending to tasks in sequence, as they become ready (that is, according to their order of arrival in the queue of ready tasks). This algorithm is known as FCFS - First Come - First Served - and its main advantage is its simplicity.


Shortest Job First (SJF) scheduling the scheduling algorithm that provides the lowest average execution and waiting times is known as the shortest task first, or SJF (Shortest Job First). As the name implies, it consists of assigning the processor to the smallest (shortest) task in the ready task queue.

## ⚠️ Prerequisite
[![Java Badge](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.oracle.com/br/java/technologies/javase-downloads.html) >= 11 


## :rocket: Installation

![](https://img.shields.io/badge/Linux-FCC624?style=for-the-badge&logo=linux&logoColor=black)

```sh
git clone https://github.com/RamonBecker/AlgoritmHash.git
```

![](https://img.shields.io/badge/Windows-0078D6?style=for-the-badge&logo=windows&logoColor=white)


```sh
git clone https://github.com/RamonBecker/AlgoritmHash.git
or install github https://desktop.github.com/ 

```

## :zap: Technologies	

- Java


## :memo: Developed features

- [x] Reading elements file
- [x] Generation of the gantt chart
- [x] Use of queues
- [x] Scheduler creation and which algorithm to use in the processes



## :technologist:	 Author

By Ramon Becker 👋🏽 Get in touch!



[<img src='https://cdn.jsdelivr.net/npm/simple-icons@3.0.1/icons/github.svg' alt='github' height='40'>](https://github.com/RamonBecker)  [<img src='https://cdn.jsdelivr.net/npm/simple-icons@3.0.1/icons/linkedin.svg' alt='linkedin' height='40'>](https://www.linkedin.com/in/https://www.linkedin.com/in/ramon-becker-da-silva-96b81b141//)
![Gmail Badge](https://img.shields.io/badge/-ramonbecker68@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:ramonbecker68@gmail.com)




