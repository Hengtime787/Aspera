------------------------------------- IMPORTANT ----------------------------------------
    If you wish to fork or use Atlas, I do kindly ask that you do not use artificial intelligence to substantially rewrite it, or pass any code off as your own. As I have now way of enforcing this past the legal GPL license that is automatically in effect when you use my project, please do try to be honest, and notify me of any projects regarding the use of Atlas. Thanks!



---------- Atlas -- Voice Assistant -------------

Atlas is designed to be a blazing, innovative digital assistant, with many advanced features, such as custom wake words, customizable screen overlays, and a fixation on fast, local functions, instead of lazily handing everything to an AI. Atlas uses a custom intent engine, to minimise delay, and maximise available resources. In addition, Atlas is planned to have integrations with third-party APIs. Possible integrations include Google Maps, various music players, Home Assistant, and more.

---------- Key Features ----------

-- p- = proposed, not set

- Local and millisecond fast (outside of STT delays)
- Flexible and Customisable
- Customisable Wake Word
- Customisable OSD (for both on screen, and off screen)
- Customisable Sounds
- p Integrations with other services like Metrolist, and Google Maps
- Blackout -- Locks phone in state of mobile data and wifi on, with airplane mode, regardless of outside intervention, and renders phone useless without verification, for use against theives, except bootloader.

------- Hook Index (for backend) -------

New Changes -- UNMUTE is now MUTE_OFF

MEDIA_PLAY
MEDIA_PAUSE
MEDIA_NEXT
MEDIA_PREVIOUS
MEDIA_GET (Now Playing with notification access)

VOLUME_UP (Custom amount)
VOLUME_DOWN (Custom amount)

MUTE_ON
MUTE_OFF

BATTERY_SAVER_ON
BATTERY_SAVER_OFF
BATTERY_LEVEL_STATUS_GET - ("Charging at 20%, [with battery saver (only if battery saver is on)]")

-------------- Device Control -----------------

BLUETOOTH_ON
BLUETOOTH_OFF

WIFI_ON
WIFI_OFF

MOBILE_DATA_ON
MOBILE_DATA_OFF

AIRPLANE_ON
AIRPLANE_OFF

DND_ON
DND_OFF

BRIGHTNESS_UP (custom amount. ask how much by without or set default in settings )
BRIGHTNESS_DOWN (Same as before i mean above)
ADAPTIVE_BRIGHTNESS_ON
ADAPTIVE_BRIGHTNESS_OFF

OPEN_<Appname> pfx
SEARCH_ pfx use ai integration for a search. #most likely use gemini flash 3.5 api or pre installed models on target device if any. If not, add please implement. or add integration directly plugin through app possible downloadable

PHONE_ - may have to take different apps
MESSAGE_ - may have to take different apps

p MARK_ - may be able to take different apps? for sure integrate built in reminder profile

SHUTDOWN
REBOOT
BLACKOUT (as explained above)

------------------ External Device Control --------------------
- p EXTERNAL_ACTIVATE_ pfx
- p EXTERNAL_DEACTIVATE_ pfx
- p
- p

------------------ Third Party Libraries ---------------------

Thank you to all open source libraries listed below! I am not affiliated with any projects shown below, and am just utilising them.

-- Fasttext (https://github.com/facebookresearch/fastText/) == Used for text classification, (vital step in intent engine).

-- Crfsuite (https://github.com/chokkan/crfsuite/) == Used for argument extraction/slot filling (required for functions that need specificity, like weather, or a world clock).



############## LICENSE FILES LOCATED IN root/LISCENSES folder ###############
