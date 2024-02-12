package com.sebas.pr502.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sebas.pr502.models.Task

class TasksViewModel() : ViewModel() {
	private val _tasks: MutableLiveData<MutableList<Task>> = MutableLiveData(mutableListOf())
	val tasks: MutableLiveData<MutableList<Task>> = _tasks

	fun addTask(task: Task) {
		_tasks.value?.add(task)
	}

	fun updateTask(id: Int, task: Task) {
		if (id in 0 until _tasks.value!!.size) {
			_tasks.value!![id] = task
		}
	}

	fun deleteAllTasks() {
		_tasks.value?.clear()
	}
}