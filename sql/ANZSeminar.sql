DROP TABLE [dbo].[Event];
DROP TABLE [dbo].[Report];
DROP TABLE [dbo].[Question];

--==============================================================
-- Table: Event
--==============================================================
CREATE TABLE [dbo].[Event](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[EVENT_ID] [varchar](255) NOT NULL,
	[EVENT_URL] [varchar](255) NULL,
	[SUMMARY] [varchar](255) NULL,
	[LOCATION] [varchar](255) NULL,
	[START_TIME] [datetime] NOT NULL,
	[END_TIME] [datetime] NOT NULL,
	[TIME_ZONE] [varchar](255) NOT NULL,
	[INTRODUCTION] [varchar](1000) NULL,
	[SPEAKER] [varchar](255) NULL,
	[LEGAL] [varchar](1000) NULL,
	[SEMINAR_KIND] [varchar](255) NOT NULL,
	[YOUTUBE_URL] [varchar](255) NULL,
	[YOUTUBE_ID] [varchar](255) NULL,
	[PRIVACY_STATUS] [varchar](255) NULL,
	[QUESTION] [varchar](255) NULL,
	[URL] [varchar](255) NULL,
	[UPLOAD_IMAGE] [varchar](255) NULL,
	[UPLOAD_FILE] [varchar](255) NULL,
	[DURATION] [varchar](255) NULL,
	[HEADLINE] [varchar](255) NULL,
	[LAYOUT_TOP] [varchar](255) NULL,
	[LOGIN] [varchar](255) NOT NULL,
	[USER_NAME] [varchar](255) NOT NULL,
	[CREATE_TIME] [datetime] NOT NULL,
	[UPDATE_TIME] [datetime] NULL,
 CONSTRAINT [PK_Event] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
	
--==============================================================
-- Table: Report
--==============================================================
CREATE TABLE [dbo].[Report](
	[ID] [bigint] NOT NULL,
	[E_ID] [bigint] NOT NULL,
	[USER_ID] [varchar](255) NOT NULL,
	[OPEN_TIME] [datetime] NOT NULL,
	[WATCH_TIME] [bigint] NOT NULL,
 CONSTRAINT [PK_Report] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

--==============================================================
-- Table: Question
--==============================================================
CREATE TABLE [dbo].[Question](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[E_ID] [bigint] NOT NULL,
	[USER_ID] [varchar](255) NOT NULL,
	[TIME] [datetime] NOT NULL,
	[QUESTION] [varchar](1000) NOT NULL,
 CONSTRAINT [PK_Question] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
