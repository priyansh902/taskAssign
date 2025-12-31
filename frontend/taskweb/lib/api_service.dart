import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';

class ApiService {
  static const String baseUrl = "http://localhost:8080/api/v1";

  static Future<void> saveToken(String token) async {
    final prefs = await SharedPreferences.getInstance();
    await prefs.setString("jwt", token);
  }

  static Future<String?> getToken() async {
    final prefs = await SharedPreferences.getInstance();
    return prefs.getString("jwt");
  }

  static Future<Map<String, dynamic>> login(String email, String password) async {
    final res = await http.post(
      Uri.parse("$baseUrl/auth/login"),
      headers: {"Content-Type": "application/json"},
      body: jsonEncode({"email": email, "password": password}),
    );
    return jsonDecode(res.body);
  }

  static Future<void> register(String name, String email, String password) async {
    await http.post(
      Uri.parse("$baseUrl/auth/register"),
      headers: {"Content-Type": "application/json"},
      body: jsonEncode({"name": name, "email": email, "password": password}),
    );
  }

  static Future<List<dynamic>> getTasks() async {
    final token = await getToken();
    final res = await http.get(
      Uri.parse("$baseUrl/tasks"),
      headers: {"Authorization": "Bearer $token"},
    );
    return jsonDecode(res.body);
  }

  static Future<void> addTask(String title, String description) async {
    final token = await getToken();
    await http.post(
      Uri.parse("$baseUrl/tasks"),
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer $token"
      },
      body: jsonEncode({"title": title, "description": description}),
    );
  }

  static Future<void> deleteTask(int id) async {
    final token = await getToken();
    await http.delete(
      Uri.parse("$baseUrl/tasks/$id"),
      headers: {"Authorization": "Bearer $token"},
    );
  }
}
