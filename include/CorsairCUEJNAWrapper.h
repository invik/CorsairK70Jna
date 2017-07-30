#pragma once
#include <CUESDK.h>
#include "CorsairCUEJNAGlobal.h"

// set specified leds to some colors.The color is retained until changed by successive calls.This function does not take logical layout into account
CORSAIRCUEJNAWRAPPER_API bool JNACorsairSetLedsColors(int size, CorsairLedColor* ledsColors);

CORSAIRCUEJNAWRAPPER_API bool JNACorsairSetLedsColorsAsync(int size, CorsairLedColor* ledsColors, void(*CallbackType)(void*, bool, CorsairError), void *context);

// returns number of connected Corsair devices that support lighting control.
CORSAIRCUEJNAWRAPPER_API int JNACorsairGetDeviceCount();

// returns information about device at provided index
CORSAIRCUEJNAWRAPPER_API CorsairDeviceInfo *JNACorsairGetDeviceInfo(int deviceIndex);

// provides list of keyboard LEDs with their physical positions.
CORSAIRCUEJNAWRAPPER_API CorsairLedPositions *JNACorsairGetLedPositions();

// provides list of keyboard or mousemat LEDs with their physical positions.
CORSAIRCUEJNAWRAPPER_API CorsairLedPositions *JNACorsairGetLedPositionsByDeviceIndex(int deviceIndex);

// retrieves led id for key name taking logical layout into account.
CORSAIRCUEJNAWRAPPER_API CorsairLedId JNACorsairGetLedIdForKeyName(char keyName);

//  requestes control using specified access mode. By default client has shared control over lighting so there is no need to call CorsairRequestControl unless client requires exclusive control
CORSAIRCUEJNAWRAPPER_API bool JNACorsairRequestControl(CorsairAccessMode accessMode);

// checks file and protocol version of CUE to understand which of SDK functions can be used with this version of CUE
CORSAIRCUEJNAWRAPPER_API CorsairProtocolDetails* JNACorsairPerformProtocolHandshake();

// returns last error that occured while using any of Corsair* functions
CORSAIRCUEJNAWRAPPER_API CorsairError JNACorsairGetLastError();

//releases previously requested control for specified access mode
CORSAIRCUEJNAWRAPPER_API bool JNACorsairReleaseControl(CorsairAccessMode accessMode);