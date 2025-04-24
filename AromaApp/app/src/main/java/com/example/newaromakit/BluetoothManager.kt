package com.example.newaromakit

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket

object BluetoothManager {
    var socket: BluetoothSocket? = null
    var device: BluetoothDevice? = null

    fun isConnected(): Boolean {
        return socket?.isConnected == true
    }

    fun closeConnection() {
        try {
            socket?.close()
            socket = null
            device = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}