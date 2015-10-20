Lol Video Data Interface Introduction 

Updated at 2013.09.08 

1.request the announcers infomation
  request format: /announcers?mac=[string]&i=[int]&lo=[int]&v=[string versionOfClient]&&p=[android|ios]
  mac: string  get the device MAC
  i:	int stands the start announcer. default is 0
  lo: 	int stands the number of announcers you want request. default is 25
  v: 	string stands the client version of the app.
  p: 	string stand for the client app platform android or ios
  
  Return data:
  {"count":int, "records":[{"id":int,"name":string, "face":string,"face1":string,"face2":string},{}...]}
  What's More:
  	the count is all data count of you Mrequest, not the return data count.
  	the mac in the document stands the MAC Address of the client devices.
  	the i stands the start records of your request default 0,
  	and  the lo stands the number you want to get, default may be 20 , 25 and so no but can not be greater than
  	50, else we at most return 50 records. 
  	
2.request videos of the given announcers
	request format: /announcers/[int announcerId]?mac=[string]&i=[int]&lo=[int]&v=[string versionOfClient]&p=[android|ios]
	Return data:
	{"count":int, "videos":
		[{	"id":int, 
			"hits":int,
			"title":string,
			"snapshot":string, 
			"snapshot1":string,
			"snapshot2":string,
			"comment":int,
			"updated_at":long}]
	}
	
3.reqeust the Bulletin info
	reqeust format: /bulletins?mac=[string]&i=[int]&lo=[int]&v=[string]&p=[android|ios]
	Return Data Format:
		{"bulletins":[
			{
				"video_unit_id":int,
				"title":string,
				"snapshot":string,
				"snapshot1":string,
				"snapshot2":string
			}]
		}
		
4.request catalogs info
	request format: /catalogs?mac=[string]&v=[string]&p=[android|ios]
	return data format:
	{"catalogs":[{"id":int,"name":string,"code":string},{}]}
	
5.request the catalog videos
	request format /catalogs/[int catalog_id]?mac=[string]&i=[int]&lo=[int]&v=[string]&p=[android|ios]
	return data type: the same as 2
	
6.request episode set info
	request format /episodes?mac=[string]&i=[int]&lo=[int]&v=[string]&p=[android|ios]
	return data type:
	{"records":[{"id":int,"title":string},{}]}
	
7.request videos for the given episode 
	request format: /episodes/[int episode_id]?mac=[string]&i=[int]&lo=[int]&v=[string]&p=[android|ios]
	return data format: the same as 2
	
8.request the video which is given id
	request format /videos/[int video_id]&mac=[string]&v=[string]&p=[android|ios]
	return data format:
	{"video":{
		"tags":string
		"desc": string
		"id":int, 
		"hits":int,
		"title":string,
		"snapshot":string, 
		"snapshot1":string,
		"snapshot2":string,
		"episode_id":int,
		"convert_params":stirng
		"target_url":string
		}}
		
9.request heros set info 
	request format: /heros?mac=[string]&i=[int]&lo=[int default 25]&t=[int]&v=[string]&p=[android|ios]
	Parameters:
		t stands the heros type id
	Return data format:
	{"count":int,
	"records":{"id":int,"face":string,"flag":int,"nick":string,"name":string,"hero_types":string,
		"face1":string,"face2":string},
	"filters":{"id":int,"name":string,"type":string}}
	
10.request video set of the given hero
	request format: /heros/[int hero_id]?mac=[string]&i=[int]&lo=[int default 25]&t=[int]&v=[string]&p=[android|ios]
	return data format: the same as 2
	
11.request client version 
	request format: /version?mac=[string]&v=[string]&p=[android|ios]
	return data format:
	{"platform":string,"v":string,"vc":int,"url":string }
	
12. request video types
	request format: /filters?mac=[string]&t=[heros,video]&&v=[string]&p=[android|ios]
	parameter: t: can be heros , video default return all heros and video 
	return data format:
	{"filters":[{"id":int,"name":string,"type":string}]}
	
13. request main page for android 
	request format: /videos?mac=[string]&v=[string]&p=[android|ios]&clo=[int default 20]&hlo=[int default 25]
	return data format:
	{	
		"heros":[{}],
		"videos":[{}],
		"catalogs":[{}],
		"announcers":[{}],
		"bulletins":[{}],
		"filters":[{}]
	}

14. Video Search
	14.1 request video set by heros and announcers, catalog_id, video_types, episode_id
	request format :/videos/q?
		mac=[string]&h=[int hero_id]&a=[int announcer_id]&c=[catalog_id]
		&t=[int video_types]&e=[int episode_id]
		&i=[int]&lo=[int]&v=[string]&p=[android|ios]
	Params :
		h: int hero_id
		a: int announcer_id
		c: int catalog_id
		t: int video_types
		e: int episode_id
		i: int start record position
		lo: int the number video you wang request
	Return data format:
		{
			"count":int,
			"videos":[
				{
					"id":int,
					"hit":int,
					"title":string,
					"snapshot":string,
					"snapshot1":string
				}...
			]
		}
	14.2 request the relative Video Set by video id
	request format: /videos/q?mac=[string]&v=[string]&p=[android|ios]&r=[int video_id]&i=[int]&lo=[int]
	return data format : the same as 14.1
	
	14.3 request video set by keywords  [developmenting can not be use]
	request format /videos/q?mac=[string]&v=[string]&p=[android|ios]&k=[keyword1]&k=[keyword2]...&i=[int]&lo=[int]
	Params:
		the keywords can be more one, if the keywords are more than one , you'd better use this format: ...?k=[keywor1]&k=[keyword2]&...
	return data format: the same as 14.1 
	what's more, the system matches the keywords by the order of hero name , announcers ame, episode title, video_type
	
	
Note：
1.the request video set by keywords is in developing.
2.if  you meet any problem, please contact Me 
3.if you find any bugs, i expect your feedback 

Yang Tengfei 
hbhz.sysu.tengfei@qq.com
2013.09.08
	
	
	
	
	
	
		