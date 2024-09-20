package com.example.myapplication.utils

import android.Manifest
import com.example.myapplication.modules.card.domain.model.Card

val PERMISSIONS = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
const val URL = "https://osgqhdx2wf.execute-api.us-west-2.amazonaws.com/"
const val API_KEY = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ1cnMiLCJpc3MiOiJyYnNhcy5jbyIsImNvbXBhbnkiOiI5OTkiLCJleHAiOjE3MjY5Mjg3MDgsInVzZXIiOiJhbmRyb2lkLnRlc3QiLCJpYXQiOjE3MjY0OTY3MDgsIkdydXBvcyI6IltcIlVuaXZlcnNhbFJlY2hhcmdlclwiXSJ9.s9IsF3IBUfPvJb5mIA_IICKljt8HdXbHkiM7-TIn-LMG4Yd7N86BMEATBadnnBnLjyCTIKFnAhtDVBP-UFOmn7gxW3BLciwYbjA90cF-SC5KXqRgHIzU1BEy3691YvxJooByLReOa6eyrXZtrW3E0InCZDDbZE7sVCaSqVZt5hMTseo17TFEKJmJCQpgzgOfYyGvtFdMwrbWSDYkn0jUuKWOIM6w4DING-3xtuayQcPGy5RWxZMBBn904xm42iyDFJV4kpZJ-SJXhGhBQ3EApJUc2je36infjetFyAlKT6sAEyV8cmfYIyWtwxhrCCs--ng-DNnoWwqgxZIJJWxsGQ"

val cards = listOf(
    Card("1234567890123456", "001", "Admin", "Administrador", "B001", "Banco Popular", "Juan", "Pérez"),
    Card("2345678901234567", "002", "User", "Usuario", "B002", "Banco Santander", "María", "Gómez"),
    Card("3456789012345678", "003", "Manager", "Gerente", "B003", "BBVA", "Carlos", "Rodríguez"),
    Card("4567890123456789", "004", "User", "Usuario", "B004", "Banco de Bogotá", "Laura", "Martínez"),
    Card("5678901234567890", "005", "Admin", "Administrador", "B005", "Bancolombia", "Pedro", "López"),
    Card("6789012345678901", "006", "User", "Usuario", "B006", "Citibank", "Ana", "García"),
    Card("7890123456789012", "007", "Manager", "Gerente", "B007", "Davivienda", "José", "Hernández"),
    Card("8901234567890123", "008", "User", "Usuario", "B008", "Scotiabank", "Isabel", "Jiménez"),
    Card("9012345678901234", "009", "Admin", "Administrador", "B009", "HSBC", "Miguel", "Ramírez"),
    Card("0123456789012345", "010", "User", "Usuario", "B010", "Banco Agrario", "Elena", "Torres"),
    Card("1123456789012346", "011", "User", "Usuario", "B011", "Banco Caja Social", "Jorge", "Ruiz"),
    Card("2123456789012347", "012", "Manager", "Gerente", "B012", "Banco AV Villas", "Paula", "Castro"),
    Card("3123456789012348", "013", "Admin", "Administrador", "B013", "Banco de Occidente", "Sofía", "Vega"),
    Card("4123456789012349", "014", "User", "Usuario", "B014", "Banco Finandina", "Luis", "Moreno"),
    Card("5123456789012350", "015", "User", "Usuario", "B015", "Banco Falabella", "Diana", "Serrano"),
    Card("6123456789012351", "016", "Manager", "Gerente", "B016", "Banco Itaú", "Diego", "Peña"),
    Card("7123456789012352", "017", "User", "Usuario", "B017", "Banco Pichincha", "Patricia", "Sánchez"),
    Card("8123456789012353", "018", "Admin", "Administrador", "B018", "Banco Procredit", "Ricardo", "Álvarez"),
    Card("9123456789012354", "019", "User", "Usuario", "B019", "Banco Coopcentral", "Valeria", "Ortega"),
    Card("1012345678901235", "020", "User", "Usuario", "B020", "Banco W", "Fernando", "Mendoza")
)
