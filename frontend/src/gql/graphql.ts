/* eslint-disable */
export type Maybe<T> = T | null;
export type InputMaybe<T> = Maybe<T>;
export type Exact<T extends { [key: string]: unknown }> = {
  [K in keyof T]: T[K];
};
export type MakeOptional<T, K extends keyof T> = Omit<T, K> & {
  [SubKey in K]?: Maybe<T[SubKey]>;
};
export type MakeMaybe<T, K extends keyof T> = Omit<T, K> & {
  [SubKey in K]: Maybe<T[SubKey]>;
};
/** All built-in and custom scalars, mapped to their actual values */
export type Scalars = {
  ID: string;
  String: string;
  Boolean: boolean;
  Int: number;
  Float: number;
  /** BigDecimal */
  BigDecimal: any;
  /** BigInteger */
  BigInteger: any;
  /** Byte */
  Byte: any;
  /** Char */
  Char: any;
  /** Date */
  Date: any;
  /** DateTime */
  DateTime: any;
  /** LocalDate */
  LocalDate: any;
  /** LocalDateTime */
  LocalDateTime: any;
  /** LocalTime */
  LocalTime: any;
  /** Long */
  Long: any;
  /** OffsetDateTime */
  OffsetDateTime: any;
  /** OffsetTime */
  OffsetTime: any;
  /** Short */
  Short: any;
  /** Time */
  Time: any;
  /** UUID */
  UUID: any;
  /** Void */
  Void: any;
};

/** Mutation root */
export type Mutation = {
  __typename?: "Mutation";
  delete?: Maybe<Scalars["Void"]>;
};

/** Mutation root */
export type MutationDeleteArgs = {
  id?: InputMaybe<Scalars["Long"]>;
};

/** Query root */
export type Query = {
  __typename?: "Query";
  entityMessages?: Maybe<Array<Maybe<Gql_MessageDetail>>>;
  enumMessages?: Maybe<Array<Maybe<Gql_MessageDetail>>>;
  permissions?: Maybe<Sec_PermissionConfig>;
};

/** Query root */
export type QueryEntityMessagesArgs = {
  className?: InputMaybe<Scalars["String"]>;
  locale?: InputMaybe<Scalars["String"]>;
};

/** Query root */
export type QueryEnumMessagesArgs = {
  className?: InputMaybe<Scalars["String"]>;
  locale?: InputMaybe<Scalars["String"]>;
};

export enum SortOrder {
  Asc = "ASC",
  Desc = "DESC",
}

export type Gql_MessageDetail = {
  __typename?: "gql_MessageDetail";
  key?: Maybe<Scalars["String"]>;
  value?: Maybe<Scalars["String"]>;
};

/** expression to compare columns of type BigDecimal. All fields are combined with logical 'AND' */
export type Inp_BigDecimalFilterCondition = {
  /** equals */
  _eq?: InputMaybe<Scalars["BigDecimal"]>;
  /** greater than */
  _gt?: InputMaybe<Scalars["BigDecimal"]>;
  /** greater than or equals */
  _gte?: InputMaybe<Scalars["BigDecimal"]>;
  /** in list */
  _in?: InputMaybe<Array<InputMaybe<Scalars["BigDecimal"]>>>;
  /** is null */
  _isNull?: InputMaybe<Scalars["Boolean"]>;
  /** less that */
  _lt?: InputMaybe<Scalars["BigDecimal"]>;
  /** less than or equals */
  _lte?: InputMaybe<Scalars["BigDecimal"]>;
  /** not equals */
  _neq?: InputMaybe<Scalars["BigDecimal"]>;
  /** not in list */
  _notIn?: InputMaybe<Array<InputMaybe<Scalars["BigDecimal"]>>>;
};

/** expression to compare columns of type BigInteger. All fields are combined with logical 'AND' */
export type Inp_BigIntegerFilterCondition = {
  /** equals */
  _eq?: InputMaybe<Scalars["BigInteger"]>;
  /** greater than */
  _gt?: InputMaybe<Scalars["BigInteger"]>;
  /** greater than or equals */
  _gte?: InputMaybe<Scalars["BigInteger"]>;
  /** in list */
  _in?: InputMaybe<Array<InputMaybe<Scalars["BigInteger"]>>>;
  /** is null */
  _isNull?: InputMaybe<Scalars["Boolean"]>;
  /** less that */
  _lt?: InputMaybe<Scalars["BigInteger"]>;
  /** less than or equals */
  _lte?: InputMaybe<Scalars["BigInteger"]>;
  /** not equals */
  _neq?: InputMaybe<Scalars["BigInteger"]>;
  /** not in list */
  _notIn?: InputMaybe<Array<InputMaybe<Scalars["BigInteger"]>>>;
};

/** expression to compare columns of type Boolean. All fields are combined with logical 'AND' */
export type Inp_BooleanFilterCondition = {
  /** equals */
  _eq?: InputMaybe<Scalars["Boolean"]>;
  /** is null */
  _isNull?: InputMaybe<Scalars["Boolean"]>;
  /** not equals */
  _neq?: InputMaybe<Scalars["Boolean"]>;
};

/** expression to compare columns of type Char. All fields are combined with logical 'AND' */
export type Inp_CharFilterCondition = {
  /** equals */
  _eq?: InputMaybe<Scalars["Char"]>;
  /** in list */
  _in?: InputMaybe<Array<InputMaybe<Scalars["Char"]>>>;
  /** is null */
  _isNull?: InputMaybe<Scalars["Boolean"]>;
  /** not equals */
  _neq?: InputMaybe<Scalars["Char"]>;
  /** not in list */
  _notIn?: InputMaybe<Array<InputMaybe<Scalars["Char"]>>>;
};

/** expression to compare columns of type Date. All fields are combined with logical 'AND' */
export type Inp_DateFilterCondition = {
  /** equals */
  _eq?: InputMaybe<Scalars["Date"]>;
  /** greater than */
  _gt?: InputMaybe<Scalars["Date"]>;
  /** greater than or equals */
  _gte?: InputMaybe<Scalars["Date"]>;
  /** in list */
  _in?: InputMaybe<Array<InputMaybe<Scalars["Date"]>>>;
  /** is null */
  _isNull?: InputMaybe<Scalars["Boolean"]>;
  /** less that */
  _lt?: InputMaybe<Scalars["Date"]>;
  /** less than or equals */
  _lte?: InputMaybe<Scalars["Date"]>;
  /** not equals */
  _neq?: InputMaybe<Scalars["Date"]>;
  /** not in list */
  _notIn?: InputMaybe<Array<InputMaybe<Scalars["Date"]>>>;
};

/** expression to compare columns of type DateTime. All fields are combined with logical 'AND' */
export type Inp_DateTimeFilterCondition = {
  /** equals */
  _eq?: InputMaybe<Scalars["DateTime"]>;
  /** greater than */
  _gt?: InputMaybe<Scalars["DateTime"]>;
  /** greater than or equals */
  _gte?: InputMaybe<Scalars["DateTime"]>;
  /** in list */
  _in?: InputMaybe<Array<InputMaybe<Scalars["DateTime"]>>>;
  /** is null */
  _isNull?: InputMaybe<Scalars["Boolean"]>;
  /** less that */
  _lt?: InputMaybe<Scalars["DateTime"]>;
  /** less than or equals */
  _lte?: InputMaybe<Scalars["DateTime"]>;
  /** not equals */
  _neq?: InputMaybe<Scalars["DateTime"]>;
  /** not in list */
  _notIn?: InputMaybe<Array<InputMaybe<Scalars["DateTime"]>>>;
};

/** expression to compare columns of type Float. All fields are combined with logical 'AND' */
export type Inp_FloatFilterCondition = {
  /** equals */
  _eq?: InputMaybe<Scalars["Float"]>;
  /** greater than */
  _gt?: InputMaybe<Scalars["Float"]>;
  /** greater than or equals */
  _gte?: InputMaybe<Scalars["Float"]>;
  /** in list */
  _in?: InputMaybe<Array<InputMaybe<Scalars["Float"]>>>;
  /** is null */
  _isNull?: InputMaybe<Scalars["Boolean"]>;
  /** less that */
  _lt?: InputMaybe<Scalars["Float"]>;
  /** less than or equals */
  _lte?: InputMaybe<Scalars["Float"]>;
  /** not equals */
  _neq?: InputMaybe<Scalars["Float"]>;
  /** not in list */
  _notIn?: InputMaybe<Array<InputMaybe<Scalars["Float"]>>>;
};

/** expression to compare columns of type Int. All fields are combined with logical 'AND' */
export type Inp_IntFilterCondition = {
  /** equals */
  _eq?: InputMaybe<Scalars["Int"]>;
  /** greater than */
  _gt?: InputMaybe<Scalars["Int"]>;
  /** greater than or equals */
  _gte?: InputMaybe<Scalars["Int"]>;
  /** in list */
  _in?: InputMaybe<Array<InputMaybe<Scalars["Int"]>>>;
  /** is null */
  _isNull?: InputMaybe<Scalars["Boolean"]>;
  /** less that */
  _lt?: InputMaybe<Scalars["Int"]>;
  /** less than or equals */
  _lte?: InputMaybe<Scalars["Int"]>;
  /** not equals */
  _neq?: InputMaybe<Scalars["Int"]>;
  /** not in list */
  _notIn?: InputMaybe<Array<InputMaybe<Scalars["Int"]>>>;
};

/** expression to compare columns of type LocalDate. All fields are combined with logical 'AND' */
export type Inp_LocalDateFilterCondition = {
  /** equals */
  _eq?: InputMaybe<Scalars["LocalDate"]>;
  /** greater than */
  _gt?: InputMaybe<Scalars["LocalDate"]>;
  /** greater than or equals */
  _gte?: InputMaybe<Scalars["LocalDate"]>;
  /** in list */
  _in?: InputMaybe<Array<InputMaybe<Scalars["LocalDate"]>>>;
  /** is null */
  _isNull?: InputMaybe<Scalars["Boolean"]>;
  /** less that */
  _lt?: InputMaybe<Scalars["LocalDate"]>;
  /** less than or equals */
  _lte?: InputMaybe<Scalars["LocalDate"]>;
  /** not equals */
  _neq?: InputMaybe<Scalars["LocalDate"]>;
  /** not in list */
  _notIn?: InputMaybe<Array<InputMaybe<Scalars["LocalDate"]>>>;
};

/** expression to compare columns of type LocalDateTime. All fields are combined with logical 'AND' */
export type Inp_LocalDateTimeFilterCondition = {
  /** equals */
  _eq?: InputMaybe<Scalars["LocalDateTime"]>;
  /** greater than */
  _gt?: InputMaybe<Scalars["LocalDateTime"]>;
  /** greater than or equals */
  _gte?: InputMaybe<Scalars["LocalDateTime"]>;
  /** in list */
  _in?: InputMaybe<Array<InputMaybe<Scalars["LocalDateTime"]>>>;
  /** is null */
  _isNull?: InputMaybe<Scalars["Boolean"]>;
  /** less that */
  _lt?: InputMaybe<Scalars["LocalDateTime"]>;
  /** less than or equals */
  _lte?: InputMaybe<Scalars["LocalDateTime"]>;
  /** not equals */
  _neq?: InputMaybe<Scalars["LocalDateTime"]>;
  /** not in list */
  _notIn?: InputMaybe<Array<InputMaybe<Scalars["LocalDateTime"]>>>;
};

/** expression to compare columns of type LocalTime. All fields are combined with logical 'AND' */
export type Inp_LocalTimeFilterCondition = {
  /** equals */
  _eq?: InputMaybe<Scalars["LocalTime"]>;
  /** greater than */
  _gt?: InputMaybe<Scalars["LocalTime"]>;
  /** greater than or equals */
  _gte?: InputMaybe<Scalars["LocalTime"]>;
  /** is null */
  _isNull?: InputMaybe<Scalars["Boolean"]>;
  /** less that */
  _lt?: InputMaybe<Scalars["LocalTime"]>;
  /** less than or equals */
  _lte?: InputMaybe<Scalars["LocalTime"]>;
  /** not equals */
  _neq?: InputMaybe<Scalars["LocalTime"]>;
};

/** expression to compare columns of type Long. All fields are combined with logical 'AND' */
export type Inp_LongFilterCondition = {
  /** equals */
  _eq?: InputMaybe<Scalars["Long"]>;
  /** greater than */
  _gt?: InputMaybe<Scalars["Long"]>;
  /** greater than or equals */
  _gte?: InputMaybe<Scalars["Long"]>;
  /** in list */
  _in?: InputMaybe<Array<InputMaybe<Scalars["Long"]>>>;
  /** is null */
  _isNull?: InputMaybe<Scalars["Boolean"]>;
  /** less that */
  _lt?: InputMaybe<Scalars["Long"]>;
  /** less than or equals */
  _lte?: InputMaybe<Scalars["Long"]>;
  /** not equals */
  _neq?: InputMaybe<Scalars["Long"]>;
  /** not in list */
  _notIn?: InputMaybe<Array<InputMaybe<Scalars["Long"]>>>;
};

/** expression to compare columns of type OffsetDateTime. All fields are combined with logical 'AND' */
export type Inp_OffsetDateTimeFilterCondition = {
  /** equals */
  _eq?: InputMaybe<Scalars["OffsetDateTime"]>;
  /** greater than */
  _gt?: InputMaybe<Scalars["OffsetDateTime"]>;
  /** greater than or equals */
  _gte?: InputMaybe<Scalars["OffsetDateTime"]>;
  /** in list */
  _in?: InputMaybe<Array<InputMaybe<Scalars["OffsetDateTime"]>>>;
  /** is null */
  _isNull?: InputMaybe<Scalars["Boolean"]>;
  /** less that */
  _lt?: InputMaybe<Scalars["OffsetDateTime"]>;
  /** less than or equals */
  _lte?: InputMaybe<Scalars["OffsetDateTime"]>;
  /** not equals */
  _neq?: InputMaybe<Scalars["OffsetDateTime"]>;
  /** not in list */
  _notIn?: InputMaybe<Array<InputMaybe<Scalars["OffsetDateTime"]>>>;
};

/** expression to compare columns of type OffsetTime. All fields are combined with logical 'AND' */
export type Inp_OffsetTimeFilterCondition = {
  /** equals */
  _eq?: InputMaybe<Scalars["OffsetTime"]>;
  /** greater than */
  _gt?: InputMaybe<Scalars["OffsetTime"]>;
  /** greater than or equals */
  _gte?: InputMaybe<Scalars["OffsetTime"]>;
  /** is null */
  _isNull?: InputMaybe<Scalars["Boolean"]>;
  /** less that */
  _lt?: InputMaybe<Scalars["OffsetTime"]>;
  /** less than or equals */
  _lte?: InputMaybe<Scalars["OffsetTime"]>;
  /** not equals */
  _neq?: InputMaybe<Scalars["OffsetTime"]>;
};

/** expression to compare columns of type Short. All fields are combined with logical 'AND' */
export type Inp_ShortFilterCondition = {
  /** equals */
  _eq?: InputMaybe<Scalars["Short"]>;
  /** greater than */
  _gt?: InputMaybe<Scalars["Short"]>;
  /** greater than or equals */
  _gte?: InputMaybe<Scalars["Short"]>;
  /** in list */
  _in?: InputMaybe<Array<InputMaybe<Scalars["Short"]>>>;
  /** is null */
  _isNull?: InputMaybe<Scalars["Boolean"]>;
  /** less that */
  _lt?: InputMaybe<Scalars["Short"]>;
  /** less than or equals */
  _lte?: InputMaybe<Scalars["Short"]>;
  /** not equals */
  _neq?: InputMaybe<Scalars["Short"]>;
  /** not in list */
  _notIn?: InputMaybe<Array<InputMaybe<Scalars["Short"]>>>;
};

/** expression to compare columns of type String. All fields are combined with logical 'AND' */
export type Inp_StringFilterCondition = {
  /** contains substring */
  _contains?: InputMaybe<Scalars["String"]>;
  /** ends with substring */
  _endsWith?: InputMaybe<Scalars["String"]>;
  /** equals */
  _eq?: InputMaybe<Scalars["String"]>;
  /** in list */
  _in?: InputMaybe<Array<InputMaybe<Scalars["String"]>>>;
  /** is null */
  _isNull?: InputMaybe<Scalars["Boolean"]>;
  /** not equals */
  _neq?: InputMaybe<Scalars["String"]>;
  /** not contains substring */
  _notContains?: InputMaybe<Scalars["String"]>;
  /** not in list */
  _notIn?: InputMaybe<Array<InputMaybe<Scalars["String"]>>>;
  /** starts with substring */
  _startsWith?: InputMaybe<Scalars["String"]>;
};

/** expression to compare columns of type Time. All fields are combined with logical 'AND' */
export type Inp_TimeFilterCondition = {
  /** equals */
  _eq?: InputMaybe<Scalars["Time"]>;
  /** greater than */
  _gt?: InputMaybe<Scalars["Time"]>;
  /** greater than or equals */
  _gte?: InputMaybe<Scalars["Time"]>;
  /** is null */
  _isNull?: InputMaybe<Scalars["Boolean"]>;
  /** less that */
  _lt?: InputMaybe<Scalars["Time"]>;
  /** less than or equals */
  _lte?: InputMaybe<Scalars["Time"]>;
  /** not equals */
  _neq?: InputMaybe<Scalars["Time"]>;
};

/** expression to compare columns of type UUID. All fields are combined with logical 'AND' */
export type Inp_UuidFilterCondition = {
  /** equals */
  _eq?: InputMaybe<Scalars["UUID"]>;
  /** in list */
  _in?: InputMaybe<Array<InputMaybe<Scalars["UUID"]>>>;
  /** is null */
  _isNull?: InputMaybe<Scalars["Boolean"]>;
  /** not equals */
  _neq?: InputMaybe<Scalars["UUID"]>;
  /** not in list */
  _notIn?: InputMaybe<Array<InputMaybe<Scalars["UUID"]>>>;
};

export type Sec_Permission = {
  __typename?: "sec_Permission";
  target?: Maybe<Scalars["String"]>;
  value?: Maybe<Scalars["Int"]>;
};

export type Sec_PermissionConfig = {
  __typename?: "sec_PermissionConfig";
  entities?: Maybe<Array<Maybe<Sec_Permission>>>;
  entityAttributes?: Maybe<Array<Maybe<Sec_Permission>>>;
  specifics?: Maybe<Array<Maybe<Sec_Permission>>>;
};
