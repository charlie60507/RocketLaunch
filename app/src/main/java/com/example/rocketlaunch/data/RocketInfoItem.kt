import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RocketInfoItem(
    @SerializedName("crew")
    val crew: List<String?>,
    @SerializedName("details")
    val details: String,
    @SerializedName("flight_number")
    val flightNumber: Int,
    @SerializedName("is_tentative")
    val isTentative: Boolean,
    @SerializedName("last_date_update")
    val lastDateUpdate: String,
    @SerializedName("last_ll_launch_date")
    val lastLlLaunchDate: String,
    @SerializedName("last_ll_update")
    val lastLlUpdate: String,
    @SerializedName("last_wiki_launch_date")
    val lastWikiLaunchDate: String,
    @SerializedName("last_wiki_revision")
    val lastWikiRevision: String,
    @SerializedName("last_wiki_update")
    val lastWikiUpdate: String,
    @SerializedName("launch_date_local")
    val launchDateLocal: String,
    @SerializedName("launch_date_source")
    val launchDateSource: String,
    @SerializedName("launch_date_unix")
    val launchDateUnix: Int,
    @SerializedName("launch_date_utc")
    val launchDateUtc: String,
    @SerializedName("launch_failure_details")
    val launchFailureDetails: LaunchFailureDetails,
    @SerializedName("launch_site")
    val launchSite: LaunchSite,
    @SerializedName("launch_success")
    val launchSuccess: Boolean,
    @SerializedName("launch_window")
    val launchWindow: Int,
    @SerializedName("launch_year")
    val launchYear: String,
    @SerializedName("links")
    val links: Links,
    @SerializedName("mission_id")
    val missionId: List<String>,
    @SerializedName("mission_name")
    val missionName: String,
    @SerializedName("rocket")
    val rocket: Rocket,
    @SerializedName("ships")
    val ships: List<String>,
    @SerializedName("static_fire_date_unix")
    val staticFireDateUnix: Int,
    @SerializedName("static_fire_date_utc")
    val staticFireDateUtc: String,
    @SerializedName("tbd")
    val tbd: Boolean,
    @SerializedName("telemetry")
    val telemetry: Telemetry,
    @SerializedName("tentative_max_precision")
    val tentativeMaxPrecision: String,
    @SerializedName("timeline")
    val timeline: Timeline,
    @SerializedName("upcoming")
    val upcoming: Boolean
) : Parcelable

@Parcelize
data class LaunchFailureDetails(
    @SerializedName("altitude")
    val altitude: Int,
    @SerializedName("reason")
    val reason: String,
    @SerializedName("time")
    val time: Int
) : Parcelable

@Parcelize
data class LaunchSite(
    @SerializedName("site_id")
    val siteId: String,
    @SerializedName("site_name")
    val siteName: String,
    @SerializedName("site_name_long")
    val siteNameLong: String
) : Parcelable

@Parcelize
data class Links(
    @SerializedName("article_link")
    val articleLink: String,
    @SerializedName("flickr_images")
    val flickrImages: List<String>,
    @SerializedName("mission_patch")
    val missionPatch: String,
    @SerializedName("mission_patch_small")
    val missionPatchSmall: String,
    @SerializedName("presskit")
    val presskit: String,
    @SerializedName("reddit_campaign")
    val redditCampaign: String,
    @SerializedName("reddit_launch")
    val redditLaunch: String,
    @SerializedName("reddit_media")
    val redditMedia: String,
    @SerializedName("reddit_recovery")
    val redditRecovery: String,
    @SerializedName("video_link")
    val videoLink: String,
    @SerializedName("wikipedia")
    val wikipedia: String,
    @SerializedName("youtube_id")
    val youtubeId: String
) : Parcelable

@Parcelize
data class Rocket(
    @SerializedName("fairings")
    val fairings: Fairings,
    @SerializedName("first_stage")
    val firstStage: FirstStage,
    @SerializedName("rocket_id")
    val rocketId: String,
    @SerializedName("rocket_name")
    val rocketName: String,
    @SerializedName("rocket_type")
    val rocketType: String,
    @SerializedName("second_stage")
    val secondStage: SecondStage
) : Parcelable

@Parcelize
data class Telemetry(
    @SerializedName("flight_club")
    val flightClub: String
) : Parcelable

@Parcelize
data class Timeline(
    @SerializedName("beco")
    val beco: Int,
    @SerializedName("center_core_boostback")
    val centerCoreBoostback: Int,
    @SerializedName("center_core_entry_burn")
    val centerCoreEntryBurn: Int,
    @SerializedName("center_core_landing")
    val centerCoreLanding: Int,
    @SerializedName("center_stage_sep")
    val centerStageSep: Int,
    @SerializedName("dragon_bay_door_deploy")
    val dragonBayDoorDeploy: Int,
    @SerializedName("dragon_separation")
    val dragonSeparation: Int,
    @SerializedName("dragon_solar_deploy")
    val dragonSolarDeploy: Int,
    @SerializedName("engine_chill")
    val engineChill: Int,
    @SerializedName("fairing_deploy")
    val fairingDeploy: Int,
    @SerializedName("first_stage_boostback_burn")
    val firstStageBoostbackBurn: Int,
    @SerializedName("first_stage_entry_burn")
    val firstStageEntryBurn: Int,
    @SerializedName("first_stage_landing")
    val firstStageLanding: Int,
    @SerializedName("first_stage_landing_burn")
    val firstStageLandingBurn: Int,
    @SerializedName("go_for_launch")
    val goForLaunch: Int,
    @SerializedName("go_for_prop_loading")
    val goForPropLoading: Int,
    @SerializedName("ignition")
    val ignition: Int,
    @SerializedName("liftoff")
    val liftoff: Int,
    @SerializedName("maxq")
    val maxq: Int,
    @SerializedName("meco")
    val meco: Int,
    @SerializedName("payload_deploy")
    val payloadDeploy: Int,
    @SerializedName("payload_deploy_1")
    val payloadDeploy1: Int,
    @SerializedName("payload_deploy_2")
    val payloadDeploy2: Int,
    @SerializedName("prelaunch_checks")
    val prelaunchChecks: Int,
    @SerializedName("propellant_pressurization")
    val propellantPressurization: Int,
    @SerializedName("rp1_loading")
    val rp1Loading: Int,
    @SerializedName("seco-1")
    val seco1: Int,
    @SerializedName("seco-2")
    val seco2: Int,
    @SerializedName("seco-3")
    val seco3: Int,
    @SerializedName("seco-4")
    val seco4: Int,
    @SerializedName("second_stage_ignition")
    val secondStageIgnition: Int,
    @SerializedName("second_stage_restart")
    val secondStageRestart: Int,
    @SerializedName("side_core_boostback")
    val sideCoreBoostback: Int,
    @SerializedName("side_core_entry_burn")
    val sideCoreEntryBurn: Int,
    @SerializedName("side_core_landing")
    val sideCoreLanding: Int,
    @SerializedName("side_core_sep")
    val sideCoreSep: Int,
    @SerializedName("stage1_lox_loading")
    val stage1LoxLoading: Int,
    @SerializedName("stage1_rp1_loading")
    val stage1Rp1Loading: Int,
    @SerializedName("stage2_lox_loading")
    val stage2LoxLoading: Int,
    @SerializedName("stage2_rp1_loading")
    val stage2Rp1Loading: Int,
    @SerializedName("stage_sep")
    val stageSep: Int,
    @SerializedName("webcast_launch")
    val webcastLaunch: Int,
    @SerializedName("webcast_liftoff")
    val webcastLiftoff: Int
) : Parcelable

@Parcelize
data class Fairings(
    @SerializedName("recovered")
    val recovered: Boolean,
    @SerializedName("recovery_attempt")
    val recoveryAttempt: Boolean,
    @SerializedName("reused")
    val reused: Boolean,
    @SerializedName("ship")
    val ship: String
) : Parcelable

@Parcelize
data class FirstStage(
    @SerializedName("cores")
    val cores: List<Core>
) : Parcelable

@Parcelize
data class SecondStage(
    @SerializedName("block")
    val block: Int,
    @SerializedName("payloads")
    val payloads: List<Payload>
) : Parcelable

@Parcelize
data class Core(
    @SerializedName("block")
    val block: Int,
    @SerializedName("core_serial")
    val coreSerial: String,
    @SerializedName("flight")
    val flight: Int,
    @SerializedName("gridfins")
    val gridfins: Boolean,
    @SerializedName("land_success")
    val landSuccess: Boolean,
    @SerializedName("landing_intent")
    val landingIntent: Boolean,
    @SerializedName("landing_type")
    val landingType: String,
    @SerializedName("landing_vehicle")
    val landingVehicle: String,
    @SerializedName("legs")
    val legs: Boolean,
    @SerializedName("reused")
    val reused: Boolean
) : Parcelable

@Parcelize
data class Payload(
    @SerializedName("cap_serial")
    val capSerial: String,
    @SerializedName("cargo_manifest")
    val cargoManifest: String,
    @SerializedName("customers")
    val customers: List<String>,
    @SerializedName("flight_time_sec")
    val flightTimeSec: Int,
    @SerializedName("manufacturer")
    val manufacturer: String,
    @SerializedName("mass_returned_kg")
    val massReturnedKg: Double,
    @SerializedName("mass_returned_lbs")
    val massReturnedLbs: Double,
    @SerializedName("nationality")
    val nationality: String,
    @SerializedName("norad_id")
    val noradId: List<Int>,
    @SerializedName("orbit")
    val orbit: String,
    @SerializedName("orbit_params")
    val orbitParams: OrbitParams,
    @SerializedName("payload_id")
    val payloadId: String,
    @SerializedName("payload_mass_kg")
    val payloadMassKg: Double,
    @SerializedName("payload_mass_lbs")
    val payloadMassLbs: Double,
    @SerializedName("payload_type")
    val payloadType: String,
    @SerializedName("reused")
    val reused: Boolean,
    @SerializedName("uid")
    val uid: String
) : Parcelable

@Parcelize
data class OrbitParams(
    @SerializedName("apoapsis_km")
    val apoapsisKm: Double,
    @SerializedName("arg_of_pericenter")
    val argOfPericenter: Double,
    @SerializedName("eccentricity")
    val eccentricity: Double,
    @SerializedName("epoch")
    val epoch: String,
    @SerializedName("inclination_deg")
    val inclinationDeg: Double,
    @SerializedName("lifespan_years")
    val lifespanYears: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("mean_anomaly")
    val meanAnomaly: Double,
    @SerializedName("mean_motion")
    val meanMotion: Double,
    @SerializedName("periapsis_km")
    val periapsisKm: Double,
    @SerializedName("period_min")
    val periodMin: Double,
    @SerializedName("raan")
    val raan: Double,
    @SerializedName("reference_system")
    val referenceSystem: String,
    @SerializedName("regime")
    val regime: String,
    @SerializedName("semi_major_axis_km")
    val semiMajorAxisKm: Double
) : Parcelable