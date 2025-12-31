import 'package:flutter/material.dart';
import 'api_service.dart';
import 'register_page.dart';
import 'task_page.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({super.key});

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  final emailCtrl = TextEditingController();
  final passCtrl = TextEditingController();
  String error = "";

  void login() async {
    try {
      final res = await ApiService.login(emailCtrl.text, passCtrl.text);
      await ApiService.saveToken(res["token"]);
      Navigator.pushReplacement(
        context,
        MaterialPageRoute(builder: (_) => const TaskPage()),
      );
    } catch (_) {
      setState(() => error = "Invalid credentials");
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("Login")),
      body: Padding(
        padding: const EdgeInsets.all(16),
        child: Column(
          children: [
            TextField(controller: emailCtrl, decoration: const InputDecoration(labelText: "Email")),
            TextField(controller: passCtrl, decoration: const InputDecoration(labelText: "Password"), obscureText: true),
            ElevatedButton(onPressed: login, child: const Text("Login")),
            Text(error, style: const TextStyle(color: Colors.red)),
            TextButton(
              onPressed: () => Navigator.push(context, MaterialPageRoute(builder: (_) => const RegisterPage())),
              child: const Text("Register"),
            )
          ],
        ),
      ),
    );
  }
}
