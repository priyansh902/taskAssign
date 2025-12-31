import 'package:flutter/material.dart';
import 'api_service.dart';
import 'login_page.dart';

class TaskPage extends StatefulWidget {
  const TaskPage({super.key});

  @override
  State<TaskPage> createState() => _TaskPageState();
}

class _TaskPageState extends State<TaskPage> {
  List tasks = [];
  final titleCtrl = TextEditingController();
  final descCtrl = TextEditingController();

  void loadTasks() async {
    tasks = await ApiService.getTasks();
    setState(() {});
  }

  void addTask() async {
    await ApiService.addTask(titleCtrl.text, descCtrl.text);
    titleCtrl.clear();
    descCtrl.clear();
    loadTasks();
  }

  void deleteTask(int id) async {
    await ApiService.deleteTask(id);
    loadTasks();
  }

  @override
  void initState() {
    super.initState();
    loadTasks();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Tasks"),
        actions: [
          IconButton(
            icon: const Icon(Icons.logout),
            onPressed: () {
              Navigator.pushReplacement(
                context,
                MaterialPageRoute(builder: (_) => const LoginPage()),
              );
            },
          )
        ],
      ),
      body: Column(
        children: [
          TextField(controller: titleCtrl, decoration: const InputDecoration(labelText: "Title")),
          TextField(controller: descCtrl, decoration: const InputDecoration(labelText: "Description")),
          ElevatedButton(onPressed: addTask, child: const Text("Add Task")),
          Expanded(
            child: ListView.builder(
              itemCount: tasks.length,
              itemBuilder: (_, i) => ListTile(
                title: Text(tasks[i]["title"]),
                subtitle: Text(tasks[i]["description"] ?? ""),
                trailing: IconButton(
                  icon: const Icon(Icons.delete),
                  onPressed: () => deleteTask(tasks[i]["id"]),
                ),
              ),
            ),
          )
        ],
      ),
    );
  }
}
