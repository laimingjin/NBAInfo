#--encoding:utf-8--
# Scrapy settings for nba project
#
# For simplicity, this file contains only the most important settings by
# default. All the other settings are documented here:
#
#     http://doc.scrapy.org/en/latest/topics/settings.html
#

BOT_NAME = 'nba'

SPIDER_MODULES = ['nba.spiders'] 
NEWSPIDER_MODULE = 'nba.spiders'

# Crawl responsibly by identifying yourself (and your website) on the user-agent
#USER_AGENT = 'nba (+http://www.yourdomain.com)'


# liansai.500.com domain config
BEFORE = "c:\perBefore.txt"    # 2013/2014:177    2014/2015:215
AFTER= "c:\perAfter.txt"  # 2013/2014:980    2014/2015:1172
PLAYERS = "c:\playersName.txt" 
TO1="c:\playersBar.png"

STAR="c:\perStar.txt" 
TEAM="c:\playersTeam.txt"
SEASONS="c:\perSeasons.txt" 
TO2="c:\playerPlot.png"

WIN="c:\playersTeamWin.txt"
LOSE="c:\playersTeamLose.txt"
TIME="c:\playersTime.txt"
WINPER="c:\playersTeamPer.txt"
TO3="c:\playersTeam.png"

# odds spider config
PLAYID = "313"  #混合过关:313    让分胜负:275
PERIOD = 1   #定义爬取最近PERIOD天的历史赔率数据

PlayerID=3272

