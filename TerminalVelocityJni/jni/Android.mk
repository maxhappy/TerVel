

LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE:=engine
LOCAL_SRC_FILES :=engine/Game.cpp 
LOCAL_SRC_FILES += engine/input.h
LOCAL_SRC_FILES +=engine/engine.h 
LOCAL_SRC_FILES += GameData/GameData.h 
LOCAL_SRC_FILES +=GameData/GameData.cpp
LOCAL_SRC_FILES +=ViewManager/ViewManager.h 
LOCAL_SRC_FILES +=ViewManager/ViewManager.cpp
LOCAL_SRC_FILES +=engine/pattern.h 
LOCAL_SRC_FILES +=engine/jnifun.h drawparts/items.h engine/initialise.h engine/gui.h

# for logging
LOCAL_CFLAGS    := -Werror -fpermissive -g
LOCAL_LDLIBS    += -llog -lGLESv1_CM
APP_OPTIM=debug

include $(BUILD_SHARED_LIBRARY)
